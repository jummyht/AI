//登录界面
/**
 * * 1、view中的类用来创立界面，并获取文本框中的数据
 * 2、相应按钮监听程序，创建模型对象，传输获取的数据给模型类中的函数
 * 3、通过模型对象中的函数接收执行完成的结果，并判断结果是否为真
 * 4、创建下一个窗口对象 
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
		// TODO 自动生成的方法存根
		UserLogin u1=new UserLogin();

	}
	public UserLogin()
	{
		//也可以获得一个容器
		Container ct=this.getContentPane();
		//空布局，可以根据原点坐标，任意布局
		this.setLayout(null);
		jl1=new JLabel("用户名：");
		//静态变量可以通过类名.静态成员变量来调用
		jl1.setFont(MyTools.f1);
		//设置位置
		jl1.setBounds(250, 100, 150, 15);
		//jl1.setBounds(60, 190, 150, 15);
		//放入容器中
		ct.add(jl1);
		
		jl2=new JLabel("密  码：");
		//静态变量可以通过类名.静态成员变量来调用
		jl2.setFont(MyTools.f1);
		//设置位置
		jl2.setBounds(250, 140, 150, 15);
		//jl1.setBounds(60, 190, 150, 15);
		//放入容器中
		ct.add(jl2);
		
		jt=new JTextField(20);
		jt.setFont(MyTools.f1);
		//设置位置
		jt.setBounds(310, 97, 160, 20);
		//设置下凹
	    jt.setBorder(BorderFactory.createLoweredBevelBorder());
	    ct.add(jt);
	    
		jpw=new JPasswordField(20);
		jpw.setFont(MyTools.f1);
		//设置位置
		jpw.setBounds(310, 137, 160, 20);
		//设置下凹
	    jpw.setBorder(BorderFactory.createLoweredBevelBorder());
	    ct.add(jpw);
		
		
		
		jr1=new JRadioButton("教师");
		jr1.setFont(MyTools.f1);
		//设置位置
		jr1.setBounds(310, 170, 60, 30);
		jr1.addActionListener(this);
		jr1.setActionCommand("teacher");
		ct.add(jr1);
		bg.add(jr1);
		
		jr2=new JRadioButton("学生");
		jr2.setFont(MyTools.f1);
		//设置位置
		jr2.setBounds(380, 170, 60, 30);
		jr2.addActionListener(this);
		jr2.setActionCommand("student");
		ct.add(jr2);
		bg.add(jr2);

		
		jb1=new JButton("登录");
		jb1.setFont(MyTools.f1);
		jb1.setBounds(300, 220, 70, 30);
		jb1.addActionListener(this);
		jb1.setActionCommand("login");
		ct.add(jb1);
		
		jb2=new JButton("取消");
		jb2.setFont(MyTools.f1);
		jb2.setBounds(380, 220, 70, 30);
		jb2.addActionListener(this);
		jb2.setActionCommand("cancel");
		ct.add(jb2);
		
		//创建一个内部类BackImage对象，背景图对象
		BackImage bi=new BackImage();
		//把图片位置确定
		//bi.setBounds(x, y, width, height);
		bi.setBounds(0, 0, 600, 360);
		ct.add(bi);
		this.setSize(600, 380);
		//获得本电脑屏幕的尺寸
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		//使JWindow放在正中间
		this.setLocation(width/2-200, height/2-150);
		this.setTitle("学生管理系统");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	//建立内部类
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
					// TODO 自动生成的 catch 块
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
		// TODO 自动生成的方法存根
		//设置选择单选按钮教师，设a=1；学生，a=2
		if(e.getActionCommand().equals("teacher"))
		{
			a="1";
		}
		if(e.getActionCommand().equals("student"))
		{
			a="2";
		}
		//点击了登录，但是没有选择单选按钮
		if((e.getActionCommand().equals("login"))&&a=="0")
		{
			JOptionPane.showMessageDialog(this, "请选择“教师”还是“学生”");
		}
		//选中教师，并且点击了登录按钮
		if(e.getActionCommand().equals("login")&&(a=="1"||a=="2"))
		{
//			MyClient mc=new MyClient();
			//System.out.println("sdf");
			//从文本框中取出员工号和密码
			String uid=jt.getText().trim();
			//由于getPassword返回的是数组，所以要转成string
			String ps=new String(jpw.getPassword());
			//把用户封装成一个对象，发送给服务器
			User u=new User();
			//设置老师用户和密码类类型
			u.setUserId(uid);
			u.setPasswd(ps);
		    //指明是学生还是老师
			u.setType(a);
			MyClient mc=new MyClient(u);
			//关闭登入界面
			this.dispose();
			
			
//			UserModel um=new UserModel();
//			UserModel2 um2=new UserModel2();
			//把返回的职位给res
				
			//String res=um.checkUser(u, p);
			//System.out.println(u+"  "+res);
			//只有经理和主管才能进入管理界面
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
//			        	JOptionPane.showMessageDialog(this, "欢迎您"+rs.getString(1)+"老师！");
//			        }
//				} 
//			    catch (Exception e1) 
//			    {
//					// TODO 自动生成的 catch 块
//					e1.printStackTrace();
//				}
//				StuManage st=new StuManage();
//				//关闭登入界面
//				//this.dispose();
//			}
//			else if(res.equals(""))
//			{
//				JOptionPane.showMessageDialog(this, "用户名或密码错误，请重试！");				
//			}
		}
//		if(e.getActionCommand().equals("login")&&a==2)
//		{
//			//从文本框中取出员工号和密码
//			String u=jt.getText().trim();
//			//由于getPassword返回的是数组，所以要转成string
//			String p=new String(jpw.getPassword());
//			UserModel2 um2=new UserModel2();
//			//把返回的职位给res
//			String res=um2.checkUser(u, p);
//			//System.out.println(u+"  "+res);
//			//只有经理和主管才能进入管理界面
//			if(res.equals("在籍"))
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
//			        	JOptionPane.showMessageDialog(this, "欢迎您"+rs.getString(1)+"同学！");
//			        }
//				} 
//			    catch (Exception e1) 
//			    {
//					// TODO 自动生成的 catch 块
//					e1.printStackTrace();
//				}
//			    Windows1 w1=new Windows1(u);
//			}
//			else if(res.equals("不在籍"))
//			{
//				JOptionPane.showMessageDialog(this, "该同学已经不在籍，无法登录");				
//			}
//			else if(res.equals(""))
//			{
//				JOptionPane.showMessageDialog(this, "用户名或密码错误，请重试！");				
//			}
//		}
//		
		if (e.getActionCommand().equals("cancel"))
		{
			JOptionPane.showMessageDialog(this, "将关闭对话框");
			//关闭登入界面
			this.dispose();
		}
	}

}
