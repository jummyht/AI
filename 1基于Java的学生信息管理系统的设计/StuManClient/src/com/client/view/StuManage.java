/*
 * ���ѧ������ϵͳ
 * 1����ѯ
 * 2�����ѧ��
 * */
package com.client.view;
//JFrame��
import javax.swing.*;

import com.client.model.StuModel;

import java.awt.*;
//�¼�������
import java.awt.event.*;
//jdbc
import java.sql.*;
//������
import java.util.*;
public class StuManage extends JFrame implements ActionListener
{
	//����ؼ�
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4,jb5;
	//����
	JTable jt;
	//������
	JScrollPane jsp;
	//�ı���
	JTextField jtf;
	//�½������ģ�Ͷ���
	StuModel sm=null;
	//�½���ӵ�Ԫ��
	StuAddDialog sa=null;
	//�½��޸ĶԻ���
	StuUpdDialog sd=null;
	
	//����������ݿ���Ҫ�Ķ���
	//PreparedStatement�ô�����Ҫ���ڷ���SQL���
	PreparedStatement ps=null;
	//2���õ�����(ָ�����ӵ��ĸ�����Դ���û���������)
	Connection ct=null;
	//���ؽ��
	ResultSet rs=null;
		
	public static void main(String[] args) 
	{
		// TODO �Զ����ɵķ������
		//StuManage test3=new StuManage();
	}
	//���캯��
	public StuManage()
	{
		//JPanel���������ʽ����
		jp1=new JPanel();
		jl1=new JLabel("�û���");
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		//�԰�ťʵ�ּ���
		jb1.addActionListener(this);
		jb1.setActionCommand("search");
		jb5=new JButton("��ʾȫ��");
		jb5.addActionListener(this);
		jb5.setActionCommand("all");
		//��ÿ���ؼ����뵽��һ��JPanel��
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		jp1.add(jb5);
		
		jp2=new JPanel();
		jb2=new JButton("���");	
		jb2.addActionListener(this);
		jb2.setActionCommand("add");
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb3.setActionCommand("modify");
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
		jb4.setActionCommand("delete");
		
		//��ÿ���ؼ����뵽�ڶ���JPanel��
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//��������ģ�Ͷ���
		//����û���βεĹ��캯�����ǲ�ѯ������񣬼���ʾ�������
		sm=new StuModel();
		//��ʼ��JTable
		//ԭ���ǣ�jt=new JTable(rowData,columnName);
		jt=new JTable(sm);
		//��ʼ��jsp��������
		jsp=new JScrollPane(jt);
		//�Ѿ�ʾ�Ʒ���JFrame,������ǰ�����
		//�ѹ����������м��λ��
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		//��ñ�������Ļ�ĳߴ�
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		//ʹJWindow�������м�
		this.setLocation(width/2-200, height/2-150);
		this.setSize(400,300);
		this.setVisible(true);
	}
	@Override
	//�¼�������Ҫʵ�ֵķ���
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		//�ж��ĸ���ť
		//�÷������������ڼ����������¼�Դ��ťjb1��ͬһ������
//		if(e.getSource()==jb1)
//		{
//			
//		}
		if(e.getActionCommand().equals("search"))
		{
			System.out.println("�����˲�ѯ");
			//��Ϊ�ѶԱ�����ݷ�װ��StuModel�У����ǿ��ԱȽϼ򵥵���ɲ�ѯ
			//trim()���ַ�������
			//��������ı����е��ַ���
			String name=this.jtf.getText().trim();
			if(name!="#")
			{
				String sql="select * from stu where stuName='"+name+"'";
				//�����µ�����ģ���࣬������
				 sm=new StuModel(sql);
				//����jtable
				jt.setModel(sm);
			}
				
		}
		else if(e.getActionCommand().equals("all"))
		{
			//�����µ�����ģ���࣬������
			 sm=new StuModel();
			//����jtable
			jt.setModel(sm);
		}
		else if(e.getActionCommand().equals("add"))
		{
			System.out.println("���������");
			 sa=new StuAddDialog(this,"���ѧ��",true);
			//�����µ�����ģ���࣬������
			 sm=new StuModel();
			//����jtable
			jt.setModel(sm);	
		}
		else if(e.getActionCommand().equals("modify"))
		{
			System.out.println("�������޸�");
			//��ʾ��ǰѡ���ǵڼ���
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫ�޸ĵ�һ��");
				return ;
			}
			//��ʾ�޸ĶԻ���
			sd=new StuUpdDialog(this,"�޸�ѧ��",true,sm,rowNum);
			//�����µ�����ģ���࣬������
			 sm=new StuModel();
			//����jtable
			jt.setModel(sm);
		}
		else if(e.getActionCommand().equals("delete"))
		{
			System.out.println("������ɾ��");
			//1���õ�ѧ��ID��
			//getSelectedRow�᷵���û����е���
			//�����ûѡ��᷵��-1
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫɾ����һ��");
				return ;
			}
			//�õ�ѧ��
			//ѧ���ǵ�0��
			String stuId=(String)sm.getValueAt(rowNum, 0);
			//System.out.println(stuId);
			//�������ݿ⣬���ɾ������
			try 
			{
				//1����������,�������ƣ��̶�д��
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//2���õ�����(ָ�����ӵ��ĸ�����Դ���û���������)
				ct=DriverManager.getConnection
				("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Students2","jummyht","1115101321");
				//3�����������
				ps=ct.prepareStatement("delete from stu where stuId=?");
				ps.setString(1, stuId);
				//4��ִ��
				//���ӡ�ɾ�����޸���executeUpdate(),��ѯ��executeQuery()
				ps.executeQuery();
				
			} 
			catch (Exception e1) 
			{
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			finally
			{
				try 
				{
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(ct!=null) ct.close();	
				} 
				catch (Exception e1) 
				{
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
			//�����µ�����ģ���࣬������
			sm=new StuModel();
			//����jtable
			jt.setModel(sm);
		}
	}
}

