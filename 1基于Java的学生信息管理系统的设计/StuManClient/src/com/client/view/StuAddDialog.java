package com.client.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class StuAddDialog extends JDialog implements ActionListener
{
	//定义我需要的swing组件
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3;
	
	//定义构造方法
	//owner它的父窗口，title窗口标题，modal模态窗口还是非模态窗口
	public StuAddDialog(Frame owner,String title,boolean modal)
	{
		//调用父类构造方法，达到模态对话框的效果
		super(owner,title,modal);
		jl1=new JLabel("学号");
		jl2=new JLabel("姓名");
		jl3=new JLabel("性别");
		jl4=new JLabel("年龄");
		jl5=new JLabel("籍贯");
		jl6=new JLabel("系别");
		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf5=new JTextField();
		jtf6=new JTextField();
		
		jb1=new JButton("添加");
		jb1.addActionListener(this);
		jb1.setActionCommand("add");
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		jb2.setActionCommand("cancel");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//设置布局为6行1列
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		//添加组件
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
			
		//展现
		this.setSize(300,200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO 自动生成的方法存根
		if(e.getActionCommand().equals("add"))
		{
			//对点击添加按钮后相应的动作
			//连接数据库
			Connection ct=null;
			ResultSet rs=null;
			PreparedStatement ps=null;
			//连接数据库
			try {
				//1、加载驱动
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//2、得到连接
				ct=DriverManager.getConnection
				("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Students2","jummyht","1115101321");
				String strsql="insert into stu values(?,?,?,?,?,?)";
				ps=ct.prepareStatement(strsql);
				ps.setString(1,jtf1.getText());
				ps.setString(2,jtf2.getText());
				ps.setString(3,jtf3.getText());
				ps.setInt(4,Integer.parseInt(jtf4.getText()));
				ps.setString(5,jtf5.getText());
				ps.setString(6,jtf6.getText());
				ps.executeUpdate();
				//关闭对话框
				this.dispose();
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
			finally
			{
				try 
				{
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(ct!=null) ct.close();
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
		//如果点击了取消按钮就直接关闭对话框
		else if(e.getActionCommand().equals("cancel"))
		{
			this.dispose();
		}
	}

}
