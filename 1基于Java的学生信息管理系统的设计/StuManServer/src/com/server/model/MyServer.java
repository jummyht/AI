/**
 * ����qq�����������ڼ����ȴ�ĳ��qq�ͻ���������
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
	 */	//����
	JTable jt;
	int a=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public MyServer()
	{
		try {
			
			System.out.println("���Ƿ�������9999�����С���");
			//ʱʱ�̵̿ؼ���
			while(true)
			{
				
				//��9999�Ŷ˿ڼ���
				ServerSocket ss=new ServerSocket(9999);
				//System.out.println("sdf");
				//�ȴ��ͻ��������ӣ��ú����᷵��һ��Socket����
				//���������û�еõ��ͻ��˵����������Զ������ȴ�
				Socket s=ss.accept();
				
			
				//System.out.println("jkl");
				//��ȡs�д��ݵ����ݣ�s�Ϳͻ��˵�sһ������Ϊ��ͬһ���˿ں�
				//�½���ȡ����isr����ȡs�е�����
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				//�û����д�����û�����Ϣ���û���������
				//ֻ�з�������User���ܻ�ȡ�û���Ϣ
				User u=(User)ois.readObject();
				System.out.println("���������յ��û�: "+u.getUserId()+"�����룺"+u.getPasswd());
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				//�½���ʦģ�ͣ�������֤
				if(u.getType().equals("1"))
				{
					
				    UserModel uls=new UserModel();
				    //�Ѵ�s�н��յ�����Ϣ���͵�ѧ��ģ����
				    String zhiwei=uls.checkUser(u.getUserId(), u.getPasswd());
				    u.setZhiwei(zhiwei);
				    System.out.println(zhiwei);
				    if(zhiwei.equals("����Ա")||zhiwei.equals("�ν�"))
				    {
				    	//����һ���ɹ��ĵ�¼��Ϣ��ֻ�з�������������Message����֤��Ϣ
						//�����ͻ��˰��û�����ͨ��s���͵����������÷�����ȥ��֤���û��Ƿ�Ϸ�
						//���ҷ�����֤�࣬���߿ͻ��˾������
						//�ͻ��˵ķ��ͺͽ��պͷ������෴
						//��Message��Ϊ���󣬷��͵�s�и��߿ͻ��˸��û��Ƿ�Ϸ�
					    Message m=new Message();
					    //��֤�ɹ�
						m.setMesType("1");
						//ְλ����ʦ
						m.setZhiwei("1");
						
						oos.writeObject(m);
				    }
				    else
				    {
				    	Message m=new Message();
					    //��֤���ɹ�
						m.setMesType("2");
						oos.writeObject(m);
				    }
				}
				//�����ѧ���û�
				else if(u.getType().equals("2"))
				{
					
					//�½���ʦģ�ͣ�������֤
				    UserModel2 uls=new UserModel2();
				    //�Ѵ�s�н��յ�����Ϣ���͵�ѧ��ģ����
				    String zhiwei=uls.checkUser(u.getUserId(), u.getPasswd());
				    u.setZhiwei(zhiwei);
				    System.out.println(zhiwei);
				    if(zhiwei.equals("�ڼ�"))
				    {
				    	//������֤ʧ����Ϣ��������Ҫ���´�����
						Message m=new Message();
						//��֤�ɹ�
						m.setMesType("1");
						//��ѧ��
						m.setZhiwei("2");
						//��ѧ�Ŵ���ģ��
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
					    //��֤���ɹ�
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

