/*
 * ����һ���ͻ��ˣ��������ӷ�����
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
		// TODO �Զ����ɵķ������
		//MyClient mc1=new MyClient();
	}
	public MyClient(User u)
	{
		try 
		{
			//Socket��������ĳ���������ˣ�127.0.0.1�Ƿ�������IP��9999�Ƕ˿ں�
			Socket s=new Socket("127.0.0.1", 9999);
			//���s���ӳɹ����Ϳ��Է������ݸ���������
			//�ͻ��˵�output�ǰ���Ϣ�ӿͻ��ˣ����أ����͵�������
			//�ͻ��˵�input�ǰ���Ϣ�ӷ��������͵��ͻ��ˣ����أ�
			//�Ѷ���ӿͻ��˷���������
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			//���s���ӳɹ����Ϳ��Է������ݸ���������
			//ͨ��oos�Ѷ�����Ϣ���͵�s��
			oos.writeObject(u);
			
			
			//���մӷ�������֤�õĽ����
			//1���ɹ���2��ʧ�ܣ�3����ͨ��Ϣ��
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			//ͨ��ois���Խ��ܵ��ɷ��������ͻ����Ķ���������֤�û��Ƿ�Ϸ�
			//�����Message�ǿͻ�����com.common���е���
			//�ͻ���ֻ�ܻ�ȡ����getMesType()����������Message������������setMesType()
			Message ms=(Message)ois.readObject();
			System.out.println(ms.getMesType());
			//�ͻ��˷��͵�s�е���User���󣬴ӷ���������������Message����������֤�û���¼
			if((ms.getMesType().equals("1"))&&(ms.getZhiwei().equals("1")))
			{
				//��ʦ
				JOptionPane.showMessageDialog(this, "��ӭ��ʦ��");	
		
				StuManage st=new StuManage();
			}
			else if((ms.getMesType().equals("1"))&&(ms.getZhiwei().equals("2")))
			{
				//ѧ��
				JOptionPane.showMessageDialog(this, "��ӭ��"+ms.getStuName()+"ͬѧ��");				
				Windows1 w1=new Windows1(ms);				
			}
			else
			{
				JOptionPane.showMessageDialog(this, "�û������������");
			}
		} 
		catch (Exception e) 
		{
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	

}

