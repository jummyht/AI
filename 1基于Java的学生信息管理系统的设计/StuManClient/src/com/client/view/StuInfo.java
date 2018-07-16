package com.client.view;
/**
 *������ʾ��ʶ����Ľ��� 
 */
import java.awt.*;
import javax.swing.*;

import com.client.model.*;
import com.common.Message;
//import com.db.SqlHelper;
import com.tools.ImagePanel;
import com.tools.MyTools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class StuInfo extends JPanel implements ActionListener 
{
	//�������񲼾�
	//JPanel p1;
    JLabel jl11,jl12,jl21,jl22,jl31,jl32,jl41,jl42;

    JButton jb1,jb2;
	JTextField jtf11,jtf12,jtf21,jtf22,jtf31,jtf32,jtf41,jtf42;
	static String num=new String();
	
	//��ʼ���������
	public void init()
	{
		//�ղ��֣����Ը���ԭ�����꣬���Ⲽ��
		this.setLayout(null);
		//���ܵ�p1����Ϊ���񲼾�4*4
		//p1=new JPanel(new GridLayout(5,4));
		jl11=new JLabel("ѧ�ţ�");
		jl11.setFont(MyTools.f1);
		//����λ��
		jl11.setBounds(30, 30, 150, 15);
		this.add(jl11);
		jtf11=new JTextField(10);
		jtf11.setEditable(false);
		jtf11.setBounds(70, 28, 150, 20);
		this.add(jtf11);
				
		jl21=new JLabel("������");
		jl21.setFont(MyTools.f1);
		jl21.setBounds(30, 60, 150, 15);
		this.add(jl21);
		jtf21=new JTextField(10);
		jtf21.setEditable(false);
		jtf21.setBounds(70, 58, 150, 20);
		this.add(jtf21);
				
		jl31=new JLabel("�Ա�");
		jl31.setFont(MyTools.f1);
		jl31.setBounds(30, 90, 150, 15);
		this.add(jl31);
		jtf31=new JTextField(10);
		jtf31.setEditable(false);
		jtf31.setBounds(70, 88, 150, 20);
		this.add(jtf31);
				
		jl41=new JLabel("���䣺");
		jl41.setFont(MyTools.f1);
		jl41.setBounds(30, 120, 150, 15);
		this.add(jl41);
		jtf41=new JTextField(10);
		jtf41.setEditable(false);
		jtf41.setBounds(70, 118, 150, 20);
		this.add(jtf41);
				
//		jb1=new JButton("ȷ��");
//		jb1.setFont(MyTools.f1);
//		jb1.setBounds(330, 160, 80, 20);
//		jb1.addActionListener(this);
//		jb1.setActionCommand("sure");
//		this.add(jb1);
				
		jl12=new JLabel("���᣺");
		jl12.setFont(MyTools.f1);
		jl12.setBounds(250, 30, 150, 15);
		this.add(jl12);
		jtf12=new JTextField(10);
		jtf12.setEditable(false);
		jtf12.setBounds(290, 28, 150, 20);
		this.add(jtf12);
				
				
		jl22=new JLabel("Ժϵ��");
		jl22.setFont(MyTools.f1);
		jl22.setBounds(250, 60, 150, 15);
		this.add(jl22);
		jtf22=new JTextField(10);
		jtf22.setEditable(false);
		jtf22.setBounds(290, 58, 150, 20);
		this.add(jtf22);
				
		jl32=new JLabel("���룺");
		jl32.setFont(MyTools.f1);
		jl32.setBounds(250,90,150,15);
		this.add(jl32);
		jtf32=new JTextField(10);
		jtf32.setEditable(false);
		jtf32.setBounds(290,88,150,20);
		this.add(jtf32);
				
		jl42=new JLabel("ѧ����");
		jl42.setFont(MyTools.f1);
		jl42.setBounds(250,120,150,15);
		this.add(jl42);
		jtf42=new JTextField(10);
		jtf42.setEditable(false);
		jtf42.setBounds(290,118,150,20);
		this.add(jtf42);
				
//		jb2=new JButton("�޸Ĳ�����");
//		jb2.setFont(MyTools.f1);
//		jb2.setBounds(100, 160, 130, 20);
//		jb2.addActionListener(this);
//		jb2.setActionCommand("modify");
//		this.add(jb2);
		
		
		this.setVisible(true);		
	}


	public void initText(String info[])
	{
		jtf11.setText(info[0]);
		jtf21.setText(info[1]);
		jtf31.setText(info[2]);
		jtf41.setText(info[3]);
		jtf12.setText(info[4]);
		jtf22.setText(info[5]);
		jtf32.setText(info[6]);
		jtf42.setText(info[7]);
	}

	public StuInfo(Message ms)
	{
		
		//��ȡ����
		this.init();
		//������ݿ�ı���
		String info[]={"","","","","","","",""};
		info[0]=ms.getStuId();
		info[1]=ms.getStuName();
		info[2]=ms.getStuSex();
		info[3]=ms.getStuAge();
		info[4]=ms.getStuJg();
		info[5]=ms.getStuDept();
		info[6]=ms.getStups();
		info[7]=ms.getStupos();
		this.initText(info);
	}
//	public String[] show(String stuId)
//	{
//		//��Ϊ���ò�ѯ����ѯ����ְλ
//		   String info[] ={"","","","","","","",""};
//		   SqlHelper sp=null;
//		   try 
//		   {
//			   //��֯SQL���Ͳ����б�
//		       //String sql="select ��������.ְλ from ����,�������� where ����.Ա����=��������.Ա���� and ����.Ա����=? and ����.����=?";
//			   String sql="select * from stu where stuId=?";
//			   String paras[]={stuId};
//		       sp=new SqlHelper();
//		       ResultSet rs=sp.query(sql, paras);
//		       //System.out.println("jkl");
//		       //����н����Ҳ������ƥ���Ա���ź�������ˣ�����ְλ
//		       ResultSetMetaData rsmt=rs.getMetaData();
//			   if(rs.next())
//			   {
//				  //ȡ��ְλ
//				  //��ȡ�� ResultSet����ĵ�ǰ����ָ���е�ֵ
//				  for(int i=0;i<8;i++)
//				  {
//					  info[i]=rs.getString(i+1);
//				  }				  
//			   }
//		   } 
//		   catch (SQLException e) 
//		   {
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		   }
//		   finally
//		   {
//			   sp.close();
//		   }
//		   //System.out.println("sdf");
//		   return info;
//	}
//	public StuInfo(StuModel sm,int rowNames)
//	{
//		this.init();
//		//��ʼ������
//		jtf11.setText((String) sm.getValueAt(rowNames, 0));
//		//����ѧ�������������Բ������޸�
//		jtf11.setEditable(false);
//		jtf21.setText((String) sm.getValueAt(rowNames, 1));
//	    jtf31.setText((String) sm.getValueAt(rowNames, 2));
//		//������ת���ַ���
//		jtf41.setText((String) sm.getValueAt(rowNames, 3).toString());
//		jtf12.setText((String) sm.getValueAt(rowNames, 4));
//		jtf22.setText((String) sm.getValueAt(rowNames, 5));
//		jtf32.setText((String) sm.getValueAt(rowNames, 7));		
//
//	}	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO �Զ����ɵķ������
		
//			if(e.getActionCommand().equals("modify"))
//			{
//				//�Ե����Ӱ�ť����Ӧ�Ķ���
//				//�������ݿ�
////				Connection ct=null;
////				Statement stmt=null;
////				ResultSet rs=null;
////				PreparedStatement ps=null;
//				String text=new String();
//				//�������ݿ�
//				try {
//					//1����������
////					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////					//2���õ�����
////					ct=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Students2","jummyht","1115101321");
////					String strsql="update stu set stups=? where stuId='"+num+"'";	
////					ps=ct.prepareStatement(strsql);
////					ps.setString(1,jtf32.getText());
////					text=jtf32.getText();
////					ps.executeUpdate();
//					//�رնԻ���
//					//this.dispose();
//					Message ms=new Message();
//					ms.setStups(jtf32.getText());
//					ms.setStuId(jtf11.getText());
//					text=jtf32.getText();
//					//Socket��������ĳ���������ˣ�127.0.0.1�Ƿ�������IP��9999�Ƕ˿ں�
//					Socket s=new Socket("127.0.0.1", 9999);
//					//�Ѷ���ӿͻ��˷���������
//					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
//					//���s���ӳɹ����Ϳ��Է������ݸ���������
//					//ͨ��oos�Ѷ�����Ϣ���͵�s��
//					oos.writeObject(ms);
//					ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
//					//ͨ��ois���Խ��ܵ��ɷ��������ͻ����Ķ���������֤�û��Ƿ�Ϸ�
//					//�����Message�ǿͻ�����com.common���е���
//					//�ͻ���ֻ�ܻ�ȡ����getMesType()����������Message������������setMesType()
//					Message ms2=(Message)ois.readObject();
//					
//					JOptionPane.showMessageDialog(this, "�޸�����Ϊ:'"+ms2.getStups()+"'�ɹ���");
//				} 
//				catch (Exception e2) 
//				{
//					e2.printStackTrace();
//				}
//				finally
//				{
//					try 
//					{
////						if(rs!=null) rs.close();
////						if(ps!=null) ps.close();
////						if(ct!=null) ct.close();
//					} 
//					catch (Exception e1) 
//					{
//						e1.printStackTrace();
//					}
//				}
//			}
	}
	
}
