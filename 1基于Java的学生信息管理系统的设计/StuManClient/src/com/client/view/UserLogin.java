//��¼����
/**
 * * 1��view�е��������������棬����ȡ�ı����е�����
 * 2����Ӧ��ť�������򣬴���ģ�Ͷ��󣬴����ȡ�����ݸ�ģ�����еĺ���
 * 3��ͨ��ģ�Ͷ����еĺ�������ִ����ɵĽ�������жϽ���Ƿ�Ϊ��
 * 4��������һ�����ڶ��� 
 */
package com.client.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.client.model.*;
import com.common.Message;
import com.common.User;
//import com.db.SqlHelper;
import com.tools.*;


public class UserLogin extends JFrame implements ActionListener
{
	JLabel jl1,jl2,jl3,jl4,jl5;
	JButton jb1,jb2;
	JRadioButton jr1,jr2;
	String a="0";
	JTextField jt;
	JPasswordField jpw;
	
	StuInfo si=null;

	ButtonGroup bg=new ButtonGroup();
	

	public static void main(String[] args) 
	{
		// TODO �Զ����ɵķ������
		UserLogin u1=new UserLogin();

	}
	public UserLogin()
	{
		//Ҳ���Ի��һ������
		Container ct=this.getContentPane();
		//�ղ��֣����Ը���ԭ�����꣬���Ⲽ��
		this.setLayout(null);
		jl1=new JLabel("�û�����");
		//��̬��������ͨ������.��̬��Ա����������
		jl1.setFont(MyTools.f1);
		//����λ��
		jl1.setBounds(250, 100, 150, 15);
		//jl1.setBounds(60, 190, 150, 15);
		//����������
		ct.add(jl1);
		
		jl2=new JLabel("��  �룺");
		//��̬��������ͨ������.��̬��Ա����������
		jl2.setFont(MyTools.f1);
		//����λ��
		jl2.setBounds(250, 140, 150, 15);
		//jl1.setBounds(60, 190, 150, 15);
		//����������
		ct.add(jl2);
		
		jt=new JTextField(20);
		jt.setFont(MyTools.f1);
		//����λ��
		jt.setBounds(310, 97, 160, 20);
		//�����°�
	    jt.setBorder(BorderFactory.createLoweredBevelBorder());
	    ct.add(jt);
	    
		jpw=new JPasswordField(20);
		jpw.setFont(MyTools.f1);
		//����λ��
		jpw.setBounds(310, 137, 160, 20);
		//�����°�
	    jpw.setBorder(BorderFactory.createLoweredBevelBorder());
	    ct.add(jpw);
		
		
		
		jr1=new JRadioButton("��ʦ");
		jr1.setFont(MyTools.f1);
		//����λ��
		jr1.setBounds(310, 170, 60, 30);
		jr1.addActionListener(this);
		jr1.setActionCommand("teacher");
		ct.add(jr1);
		bg.add(jr1);
		
		jr2=new JRadioButton("ѧ��");
		jr2.setFont(MyTools.f1);
		//����λ��
		jr2.setBounds(380, 170, 60, 30);
		jr2.addActionListener(this);
		jr2.setActionCommand("student");
		ct.add(jr2);
		bg.add(jr2);

		
		jb1=new JButton("��¼");
		jb1.setFont(MyTools.f1);
		jb1.setBounds(300, 220, 70, 30);
		jb1.addActionListener(this);
		jb1.setActionCommand("login");
		ct.add(jb1);
		
		jb2=new JButton("ȡ��");
		jb2.setFont(MyTools.f1);
		jb2.setBounds(380, 220, 70, 30);
		jb2.addActionListener(this);
		jb2.setActionCommand("cancel");
		ct.add(jb2);
		
		//����һ���ڲ���BackImage���󣬱���ͼ����
		BackImage bi=new BackImage();
		//��ͼƬλ��ȷ��
		//bi.setBounds(x, y, width, height);
		bi.setBounds(0, 0, 600, 360);
		ct.add(bi);
		this.setSize(600, 380);
		//��ñ�������Ļ�ĳߴ�
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		//ʹJWindow�������м�
		this.setLocation(width/2-200, height/2-150);
		this.setTitle("ѧ������ϵͳ");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	//�����ڲ���
		class BackImage extends JPanel
		{
			Image im;
			public BackImage()
			{
				try 
				{
					im=ImageIO.read(new File("image\\login.PNG"));
				} 
				catch (IOException e) 
				{
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
			public void paintComponent(Graphics g)
			{
				//g.drawImage(img, x, y, width, height, observer)
				g.drawImage(im, 0, 0, 600,360,this);
			}
		}
 
		Message ms=new Message();
		public UserLogin(Message ms)
		{
			this.ms=ms;
		}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO �Զ����ɵķ������
		//����ѡ��ѡ��ť��ʦ����a=1��ѧ����a=2
		if(e.getActionCommand().equals("teacher"))
		{
			a="1";
		}
		if(e.getActionCommand().equals("student"))
		{
			a="2";
		}
		//����˵�¼������û��ѡ��ѡ��ť
		if((e.getActionCommand().equals("login"))&&a=="0")
		{
			JOptionPane.showMessageDialog(this, "��ѡ�񡰽�ʦ�����ǡ�ѧ����");
		}
		//ѡ�н�ʦ�����ҵ���˵�¼��ť
		if(e.getActionCommand().equals("login")&&(a=="1"||a=="2"))
		{
//			MyClient mc=new MyClient();
			//System.out.println("sdf");
			//���ı�����ȡ��Ա���ź�����
			String uid=jt.getText().trim();
			//����getPassword���ص������飬����Ҫת��string
			String ps=new String(jpw.getPassword());
			//���û���װ��һ�����󣬷��͸�������
			User u=new User();
			//������ʦ�û�������������
			u.setUserId(uid);
			u.setPasswd(ps);
		    //ָ����ѧ��������ʦ
			u.setType(a);
			MyClient mc=new MyClient(u);
			//�رյ������
			this.dispose();
			
			
//			UserModel um=new UserModel();
//			UserModel2 um2=new UserModel2();
			//�ѷ��ص�ְλ��res
				
			//String res=um.checkUser(u, p);
			//System.out.println(u+"  "+res);
			//ֻ�о�������ܲ��ܽ���������
//			if()
//			{
//				try 
//			    {
//			    	SqlHelper sp=null;
//			        String sql="select teaNa from teacher where teaId=?";	
//			        String paras[]={u};
//			        sp=new SqlHelper();
//			        ResultSet rs=sp.query(sql, paras);
//			        if(rs.next())
//			        {
//			        	JOptionPane.showMessageDialog(this, "��ӭ��"+rs.getString(1)+"��ʦ��");
//			        }
//				} 
//			    catch (Exception e1) 
//			    {
//					// TODO �Զ����ɵ� catch ��
//					e1.printStackTrace();
//				}
//				StuManage st=new StuManage();
//				//�رյ������
//				//this.dispose();
//			}
//			else if(res.equals(""))
//			{
//				JOptionPane.showMessageDialog(this, "�û�����������������ԣ�");				
//			}
		}
//		if(e.getActionCommand().equals("login")&&a==2)
//		{
//			//���ı�����ȡ��Ա���ź�����
//			String u=jt.getText().trim();
//			//����getPassword���ص������飬����Ҫת��string
//			String p=new String(jpw.getPassword());
//			UserModel2 um2=new UserModel2();
//			//�ѷ��ص�ְλ��res
//			String res=um2.checkUser(u, p);
//			//System.out.println(u+"  "+res);
//			//ֻ�о�������ܲ��ܽ���������
//			if(res.equals("�ڼ�"))
//			{
//			    try 
//			    {
//			    	SqlHelper sp=null;
//			        String sql="select stuName from stu where stuId=?";	
//			        String paras[]={u};
//			        sp=new SqlHelper();
//			        ResultSet rs=sp.query(sql, paras);
//			        if(rs.next())
//			        {
//			        	JOptionPane.showMessageDialog(this, "��ӭ��"+rs.getString(1)+"ͬѧ��");
//			        }
//				} 
//			    catch (Exception e1) 
//			    {
//					// TODO �Զ����ɵ� catch ��
//					e1.printStackTrace();
//				}
//			    Windows1 w1=new Windows1(u);
//			}
//			else if(res.equals("���ڼ�"))
//			{
//				JOptionPane.showMessageDialog(this, "��ͬѧ�Ѿ����ڼ����޷���¼");				
//			}
//			else if(res.equals(""))
//			{
//				JOptionPane.showMessageDialog(this, "�û�����������������ԣ�");				
//			}
//		}
//		
		if (e.getActionCommand().equals("cancel"))
		{
			JOptionPane.showMessageDialog(this, "���رնԻ���");
			//�رյ������
			this.dispose();
		}
	}

}
