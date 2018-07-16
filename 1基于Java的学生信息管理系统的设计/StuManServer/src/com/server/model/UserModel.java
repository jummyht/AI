/*
 * �û�������ģ�ͣ�������ɶ��û��ĸ��ֲ���,������Ҫ��д��Ŀҵ�����
 * 1��model�е�����Ҫ��д�������β����������ַ�����������������view���������ı����е����ݣ�
 * 2���ں���������SQL��ѯ��䣬�����û�����֤
 * 3���½�SqlHelper����󣬵���SqlHelper�е�query�����������շ������Ľ����
 * */
package com.server.model;
import com.db.SqlHelper;

import java.sql.*;
public class UserModel 
{
	/**
	 * 
	 * @param uid �û����
	 * @param p �û�����
	 * @return ���ص����û���ְλ����������ڷ���null
	 */
	
	
	//�������մ��ı����л�ȡ��Ա���ź����룬
	//�����ݿ��еļ�¼�Ƚϣ�һ���򷵻ظ�Ա���ŵ��˵�ְλ
	//�Ǿ�������ܣ������������
   public String checkUser(String uid,String p)
   {
	   //��Ϊ���ò�ѯ����ѯ����ְλ
	   String zhiwei="";
	   SqlHelper sp=null;
	   try 
	   {
		   //��֯SQL���Ͳ����б�
	       //String sql="select ��������.ְλ from ����,�������� where ����.Ա����=��������.Ա���� and ����.Ա����=? and ����.����=?";
		   String sql="select teacher.teapos from teacher where teacher.teaId=? and teacher.Ps=?";
		   String paras[]={uid,p};
	       sp=new SqlHelper();
	       ResultSet rs=sp.query(sql, paras);
	       //System.out.println("jkl");
	       //����н����Ҳ������ƥ���Ա���ź�������ˣ�����ְλ
		   if(rs.next())
		   {
			  //ȡ��ְλ
			  //��ȡ�� ResultSet����ĵ�ǰ����ָ���е�ֵ
			  zhiwei=rs.getString(1);
			  
		   }
	   } 
	   catch (SQLException e) 
	   {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	   }
	   finally
	   {
		   sp.close();
	   }
	   //System.out.println("sdf");
	   return zhiwei;
   }   
}
