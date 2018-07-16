/**
 * 这是服务器控制界面的，可以启动服务器，关闭服务器，可以管理和监控用户，可以强制用户下线
 */
package com.server.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.server.model.*;
@SuppressWarnings("serial")
public class MyServerFrame extends JFrame implements ActionListener
{

	JPanel jp1;
	JButton jb1, jb2;
	MyServer ms;
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		MyServerFrame ms=new MyServerFrame();
	}
	
	public MyServerFrame()
	{
		jp1=new JPanel();
		jb1=new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2=new JButton("关闭服务器");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500,400);
		//获得本电脑屏幕的尺寸
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		//使JWindow放在正中间
		this.setLocation(width/2-200, height/2-150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1)
		{
			ms=new MyServer();
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}
}
