/*
 * 完成学生管理系统
 * 1、查询
 * 2、添加学生
 * */
package com.client.view;
//JFrame包
import javax.swing.*;

import com.client.model.StuModel;

import java.awt.*;
//事件监听包
import java.awt.event.*;
//jdbc
import java.sql.*;
//向量包
import java.util.*;
public class StuManage extends JFrame implements ActionListener
{
	//定义控件
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4,jb5;
	//表格框
	JTable jt;
	//滚动条
	JScrollPane jsp;
	//文本框
	JTextField jtf;
	//新建抽象表模型对象
	StuModel sm=null;
	//新建添加单元框
	StuAddDialog sa=null;
	//新建修改对话框
	StuUpdDialog sd=null;
	
	//定义操作数据库需要的东西
	//PreparedStatement用处：主要用于发送SQL语句
	PreparedStatement ps=null;
	//2、得到连接(指定连接到哪个数据源，用户名和密码)
	Connection ct=null;
	//返回结果
	ResultSet rs=null;
		
	public static void main(String[] args) 
	{
		// TODO 自动生成的方法存根
		//StuManage test3=new StuManage();
	}
	//构造函数
	public StuManage()
	{
		//JPanel本身就是流式布局
		jp1=new JPanel();
		jl1=new JLabel("用户名");
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		//对按钮实现监听
		jb1.addActionListener(this);
		jb1.setActionCommand("search");
		jb5=new JButton("显示全部");
		jb5.addActionListener(this);
		jb5.setActionCommand("all");
		//把每个控件加入到第一个JPanel中
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		jp1.add(jb5);
		
		jp2=new JPanel();
		jb2=new JButton("添加");	
		jb2.addActionListener(this);
		jb2.setActionCommand("add");
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb3.setActionCommand("modify");
		jb4=new JButton("删除");
		jb4.addActionListener(this);
		jb4.setActionCommand("delete");
		
		//把每个控件加入到第二个JPanel中
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//创建数据模型对象
		//调用没有形参的构造函数，是查询整个表格，即显示整个表格
		sm=new StuModel();
		//初始化JTable
		//原本是：jt=new JTable(rowData,columnName);
		jt=new JTable(sm);
		//初始化jsp，滚动条
		jsp=new JScrollPane(jt);
		//把警示牌放入JFrame,本身就是包布局
		//把滚动条放入中间的位置
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		//获得本电脑屏幕的尺寸
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		//使JWindow放在正中间
		this.setLocation(width/2-200, height/2-150);
		this.setSize(400,300);
		this.setVisible(true);
	}
	@Override
	//事件监听需要实现的方法
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		//判断哪个按钮
		//该方法仅仅适用于监听函数和事件源按钮jb1在同一个类中
//		if(e.getSource()==jb1)
//		{
//			
//		}
		if(e.getActionCommand().equals("search"))
		{
			System.out.println("你点击了查询");
			//因为把对表的数据封装在StuModel中，我们可以比较简单地完成查询
			//trim()空字符串过滤
			//获得输入文本框中的字符串
			String name=this.jtf.getText().trim();
			if(name!="#")
			{
				String sql="select * from stu where stuName='"+name+"'";
				//构件新的数据模型类，并更新
				 sm=new StuModel(sql);
				//更新jtable
				jt.setModel(sm);
			}
				
		}
		else if(e.getActionCommand().equals("all"))
		{
			//构件新的数据模型类，并更新
			 sm=new StuModel();
			//更新jtable
			jt.setModel(sm);
		}
		else if(e.getActionCommand().equals("add"))
		{
			System.out.println("你点击了添加");
			 sa=new StuAddDialog(this,"添加学生",true);
			//构建新的数据模型类，并更新
			 sm=new StuModel();
			//更新jtable
			jt.setModel(sm);	
		}
		else if(e.getActionCommand().equals("modify"))
		{
			System.out.println("你点击了修改");
			//显示当前选择是第几行
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择需要修改的一行");
				return ;
			}
			//显示修改对话框
			sd=new StuUpdDialog(this,"修改学生",true,sm,rowNum);
			//构建新的数据模型类，并更新
			 sm=new StuModel();
			//更新jtable
			jt.setModel(sm);
		}
		else if(e.getActionCommand().equals("delete"))
		{
			System.out.println("你点击了删除");
			//1、得到学生ID号
			//getSelectedRow会返回用户点中的行
			//如果都没选择会返回-1
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择需要删除的一行");
				return ;
			}
			//得到学号
			//学号是第0行
			String stuId=(String)sm.getValueAt(rowNum, 0);
			//System.out.println(stuId);
			//连接数据库，完成删除任务
			try 
			{
				//1、加载驱动,驱动名称，固定写法
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//2、得到连接(指定连接到哪个数据源，用户名和密码)
				ct=DriverManager.getConnection
				("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Students2","jummyht","1115101321");
				//3、创建火箭车
				ps=ct.prepareStatement("delete from stu where stuId=?");
				ps.setString(1, stuId);
				//4、执行
				//增加、删除、修改用executeUpdate(),查询用executeQuery()
				ps.executeQuery();
				
			} 
			catch (Exception e1) 
			{
				// TODO 自动生成的 catch 块
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
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			//构件新的数据模型类，并更新
			sm=new StuModel();
			//更新jtable
			jt.setModel(sm);
		}
	}
}

