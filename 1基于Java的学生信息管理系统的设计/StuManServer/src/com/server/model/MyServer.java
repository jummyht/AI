/**
 * 这是qq服务器，它在监听等待某个qq客户端来连接
 */
package com.server.model;
import javax.swing.*;


import com.common.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;


public class MyServer {
	/**
	 * @param args
	 */	//表格框
	JTable jt;
	int a=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public MyServer()
	{
		try {
			
			System.out.println("我是服务器在9999监听中……");
			//时时刻刻地监听
			while(true)
			{
				
				//在9999号端口监听
				ServerSocket ss=new ServerSocket(9999);
				//System.out.println("sdf");
				//等待客户端来连接，该函数会返回一个Socket连接
				//如果服务器没有得到客户端的请求，则会永远在这里等待
				Socket s=ss.accept();
				
			
				//System.out.println("jkl");
				//读取s中传递的数据，s和客户端的s一样，因为是同一个端口号
				//新建读取对象isr，读取s中的数据
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				//用户包中存放着用户的信息，用户名和密码
				//只有服务器的User才能获取用户信息
				User u=(User)ois.readObject();
				System.out.println("服务器接收到用户: "+u.getUserId()+"和密码："+u.getPasswd());
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				//新建老师模型，用于验证
				if(u.getType().equals("1"))
				{
					
				    UserModel uls=new UserModel();
				    //把从s中接收到的信息发送到学生模型中
				    String zhiwei=uls.checkUser(u.getUserId(), u.getPasswd());
				    u.setZhiwei(zhiwei);
				    System.out.println(zhiwei);
				    if(zhiwei.equals("辅导员")||zhiwei.equals("课教"))
				    {
				    	//返回一个成功的登录信息，只有服务器才能设置Message的验证信息
						//即，客户端把用户对象通过s发送到服务器，让服务器去验证该用户是否合法
						//并且发还验证类，告诉客户端具体情况
						//客户端的发送和接收和服务器相反
						//把Message作为对象，发送到s中告诉客户端该用户是否合法
					    Message m=new Message();
					    //验证成功
						m.setMesType("1");
						//职位是老师
						m.setZhiwei("1");
						
						oos.writeObject(m);
				    }
				    else
				    {
				    	Message m=new Message();
					    //验证不成功
						m.setMesType("2");
						oos.writeObject(m);
				    }
				}
				//如果是学生用户
				else if(u.getType().equals("2"))
				{
					
					//新建老师模型，用于验证
				    UserModel2 uls=new UserModel2();
				    //把从s中接收到的信息发送到学生模型中
				    String zhiwei=uls.checkUser(u.getUserId(), u.getPasswd());
				    u.setZhiwei(zhiwei);
				    System.out.println(zhiwei);
				    if(zhiwei.equals("在籍"))
				    {
				    	//设置验证失败信息，并且需要重新打开连接
						Message m=new Message();
						//验证成功
						m.setMesType("1");
						//是学生
						m.setZhiwei("2");
						//把学号传给模型
						String info[]=uls.show(u.getUserId());
						m.setStuId(info[0]);
						m.setStuName(info[1]);
						m.setStuSex(info[2]);
						m.setStuAge(info[3]);
						m.setStuJg(info[4]);
						m.setStuDept(info[5]);
						m.setStups(info[6]);
						m.setStupos(info[7]);
						oos.writeObject(m);
				    }
				    else
				    {
				    	Message m=new Message();
					    //验证不成功
						m.setMesType("2");
						oos.writeObject(m);
				    }
				}	
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{		
			
		}
	}
}

