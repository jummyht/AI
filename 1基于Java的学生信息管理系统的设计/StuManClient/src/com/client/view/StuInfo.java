package com.client.view;
/**
 *用于显示认识管理的界面 
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
	//设置网格布局
	//JPanel p1;
    JLabel jl11,jl12,jl21,jl22,jl31,jl32,jl41,jl42;

    JButton jb1,jb2;
	JTextField jtf11,jtf12,jtf21,jtf22,jtf31,jtf32,jtf41,jtf42;
	static String num=new String();
	
	//初始化组件函数
	public void init()
	{
		//空布局，可以根据原点坐标，任意布局
		this.setLayout(null);
		//把总的p1设置为网格布局4*4
		//p1=new JPanel(new GridLayout(5,4));
		jl11=new JLabel("学号：");
		jl11.setFont(MyTools.f1);
		//设置位置
		jl11.setBounds(30, 30, 150, 15);
		this.add(jl11);
		jtf11=new JTextField(10);
		jtf11.setEditable(false);
		jtf11.setBounds(70, 28, 150, 20);
		this.add(jtf11);
				
		jl21=new JLabel("姓名：");
		jl21.setFont(MyTools.f1);
		jl21.setBounds(30, 60, 150, 15);
		this.add(jl21);
		jtf21=new JTextField(10);
		jtf21.setEditable(false);
		jtf21.setBounds(70, 58, 150, 20);
		this.add(jtf21);
				
		jl31=new JLabel("性别：");
		jl31.setFont(MyTools.f1);
		jl31.setBounds(30, 90, 150, 15);
		this.add(jl31);
		jtf31=new JTextField(10);
		jtf31.setEditable(false);
		jtf31.setBounds(70, 88, 150, 20);
		this.add(jtf31);
				
		jl41=new JLabel("年龄：");
		jl41.setFont(MyTools.f1);
		jl41.setBounds(30, 120, 150, 15);
		this.add(jl41);
		jtf41=new JTextField(10);
		jtf41.setEditable(false);
		jtf41.setBounds(70, 118, 150, 20);
		this.add(jtf41);
				
//		jb1=new JButton("确定");
//		jb1.setFont(MyTools.f1);
//		jb1.setBounds(330, 160, 80, 20);
//		jb1.addActionListener(this);
//		jb1.setActionCommand("sure");
//		this.add(jb1);
				
		jl12=new JLabel("籍贯：");
		jl12.setFont(MyTools.f1);
		jl12.setBounds(250, 30, 150, 15);
		this.add(jl12);
		jtf12=new JTextField(10);
		jtf12.setEditable(false);
		jtf12.setBounds(290, 28, 150, 20);
		this.add(jtf12);
				
				
		jl22=new JLabel("院系：");
		jl22.setFont(MyTools.f1);
		jl22.setBounds(250, 60, 150, 15);
		this.add(jl22);
		jtf22=new JTextField(10);
		jtf22.setEditable(false);
		jtf22.setBounds(290, 58, 150, 20);
		this.add(jtf22);
				
		jl32=new JLabel("密码：");
		jl32.setFont(MyTools.f1);
		jl32.setBounds(250,90,150,15);
		this.add(jl32);
		jtf32=new JTextField(10);
		jtf32.setEditable(false);
		jtf32.setBounds(290,88,150,20);
		this.add(jtf32);
				
		jl42=new JLabel("学籍：");
		jl42.setFont(MyTools.f1);
		jl42.setBounds(250,120,150,15);
		this.add(jl42);
		jtf42=new JTextField(10);
		jtf42.setEditable(false);
		jtf42.setBounds(290,118,150,20);
		this.add(jtf42);
				
//		jb2=new JButton("修改并保存");
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
		
		//获取界面
		this.init();
		//存放数据库的变量
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
//		//因为设置查询语句查询的是职位
//		   String info[] ={"","","","","","","",""};
//		   SqlHelper sp=null;
//		   try 
//		   {
//			   //组织SQL语句和参数列表
//		       //String sql="select 人事资料.职位 from 登入,人事资料 where 登入.员工号=人事资料.员工号 and 登入.员工号=? and 登入.密码=?";
//			   String sql="select * from stu where stuId=?";
//			   String paras[]={stuId};
//		       sp=new SqlHelper();
//		       ResultSet rs=sp.query(sql, paras);
//		       //System.out.println("jkl");
//		       //如果有结果，也就是有匹配的员工号和密码的人，返回职位
//		       ResultSetMetaData rsmt=rs.getMetaData();
//			   if(rs.next())
//			   {
//				  //取出职位
//				  //获取此 ResultSet对象的当前行中指定列的值
//				  for(int i=0;i<8;i++)
//				  {
//					  info[i]=rs.getString(i+1);
//				  }				  
//			   }
//		   } 
//		   catch (SQLException e) 
//		   {
//			// TODO 自动生成的 catch 块
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
//		//初始化数据
//		jtf11.setText((String) sm.getValueAt(rowNames, 0));
//		//由于学号是主键，所以不允许被修改
//		jtf11.setEditable(false);
//		jtf21.setText((String) sm.getValueAt(rowNames, 1));
//	    jtf31.setText((String) sm.getValueAt(rowNames, 2));
//		//把数字转成字符串
//		jtf41.setText((String) sm.getValueAt(rowNames, 3).toString());
//		jtf12.setText((String) sm.getValueAt(rowNames, 4));
//		jtf22.setText((String) sm.getValueAt(rowNames, 5));
//		jtf32.setText((String) sm.getValueAt(rowNames, 7));		
//
//	}	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO 自动生成的方法存根
		
//			if(e.getActionCommand().equals("modify"))
//			{
//				//对点击添加按钮后相应的动作
//				//连接数据库
////				Connection ct=null;
////				Statement stmt=null;
////				ResultSet rs=null;
////				PreparedStatement ps=null;
//				String text=new String();
//				//连接数据库
//				try {
//					//1、加载驱动
////					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////					//2、得到连接
////					ct=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Students2","jummyht","1115101321");
////					String strsql="update stu set stups=? where stuId='"+num+"'";	
////					ps=ct.prepareStatement(strsql);
////					ps.setString(1,jtf32.getText());
////					text=jtf32.getText();
////					ps.executeUpdate();
//					//关闭对话框
//					//this.dispose();
//					Message ms=new Message();
//					ms.setStups(jtf32.getText());
//					ms.setStuId(jtf11.getText());
//					text=jtf32.getText();
//					//Socket函数连接某个服务器端，127.0.0.1是服务器的IP，9999是端口号
//					Socket s=new Socket("127.0.0.1", 9999);
//					//把对象从客户端发到服务器
//					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
//					//如果s连接成功，就可以发送数据给服务器了
//					//通过oos把对象信息发送到s中
//					oos.writeObject(ms);
//					ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
//					//通过ois可以接受到由服务器发送回来的对象，用于验证用户是否合法
//					//这里的Message是客户端中com.common包中的类
//					//客户端只能获取数据getMesType()，服务器的Message才能设置数据setMesType()
//					Message ms2=(Message)ois.readObject();
//					
//					JOptionPane.showMessageDialog(this, "修改密码为:'"+ms2.getStups()+"'成功！");
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
