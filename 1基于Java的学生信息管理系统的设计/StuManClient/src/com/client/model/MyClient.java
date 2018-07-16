/*
 * 这是一个客户端，可以连接服务器
 * */
package com.client.model;
import java.io.*;
import java.net.*;

import javax.swing.*;


import com.client.view.*;
import com.common.*;
public class MyClient extends JFrame
{
	
	public static void main(String[] args) 
	{
		// TODO 自动生成的方法存根
		//MyClient mc1=new MyClient();
	}
	public MyClient(User u)
	{
		try 
		{
			//Socket函数连接某个服务器端，127.0.0.1是服务器的IP，9999是端口号
			Socket s=new Socket("127.0.0.1", 9999);
			//如果s连接成功，就可以发送数据给服务器了
			//客户端的output是把信息从客户端（本地）发送到服务器
			//客户端的input是把信息从服务器发送到客户端（本地）
			//把对象从客户端发到服务器
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			//如果s连接成功，就可以发送数据给服务器了
			//通过oos把对象信息发送到s中
			oos.writeObject(u);
			
			
			//接收从服务器验证好的结果：
			//1：成功；2：失败；3、普通消息包
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			//通过ois可以接受到由服务器发送回来的对象，用于验证用户是否合法
			//这里的Message是客户端中com.common包中的类
			//客户端只能获取数据getMesType()，服务器的Message才能设置数据setMesType()
			Message ms=(Message)ois.readObject();
			System.out.println(ms.getMesType());
			//客户端发送到s中的是User对象，从服务器返回来的是Message对象用于验证用户登录
			if((ms.getMesType().equals("1"))&&(ms.getZhiwei().equals("1")))
			{
				//老师
				JOptionPane.showMessageDialog(this, "欢迎老师！");	
		
				StuManage st=new StuManage();
			}
			else if((ms.getMesType().equals("1"))&&(ms.getZhiwei().equals("2")))
			{
				//学生
				JOptionPane.showMessageDialog(this, "欢迎您"+ms.getStuName()+"同学！");				
				Windows1 w1=new Windows1(ms);				
			}
			else
			{
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
			}
		} 
		catch (Exception e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	

}

