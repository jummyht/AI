/*
 * �û�������ģ�ͣ�������ɶ��û��ĸ��ֲ���,������Ҫ��д��Ŀҵ�����
 * */
package com.server.model;
import com.db.SqlHelper;

import java.sql.*;
public class UserModel2 
{
	/**
	 * 
	 * @param uid �û����
	 * @param p �û�����
	 * @return ���ص����û���ְλ����������ڷ���null
	 */
	
	public String[] show(String stuId)
	{
		//��Ϊ���ò�ѯ����ѯ����ְλ
		   String info[] ={"","","","","","","",""};
		   SqlHelper sp=null;
		   try 
		   {
			   //��֯SQL���Ͳ����б�
		       //String sql="select ��������.ְλ from ����,�������� where ����.Ա����=��������.Ա���� and ����.Ա����=? and ����.����=?";
			   String sql="select * from stu where stuId=?";
			   String paras[]={stuId};
		       sp=new SqlHelper();
		       ResultSet rs=sp.query(sql, paras);
		       //System.out.println("jkl");
		       //����н����Ҳ������ƥ���Ա���ź�������ˣ�����ְλ
		       ResultSetMetaData rsmt=rs.getMetaData();
			   if(rs.next())
			   {
				  //ȡ��ְλ
				  //��ȡ�� ResultSet����ĵ�ǰ����ָ���е�ֵ
				  for(int i=0;i<8;i++)
				  {
					  info[i]=rs.getString(i+1);
				  }				  
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
		   return info;
	}
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
		   String sql="select stu.stupos from stu where stuId=? and stups=?";
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
