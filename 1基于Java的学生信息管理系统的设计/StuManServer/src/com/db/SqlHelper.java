/*
 * �����ݿ������
 * crud
 * 1��db���еĶ����ú����ݿ��е�����
 * 2����дquery�����������β�ΪSQL����ģ�����е������ı����е�����
 * 3�����Ѳ�ѯ���Ľ�������ؽ����
 * */
package com.db;
import java.util.*;

import javax.swing.table.AbstractTableModel;

import java.sql.*;
public class SqlHelper  
{
	PreparedStatement ps=null;
	ResultSet rs=null;
	Connection ct=null;
	String dirVerName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Students2";
	String user="jummyht";
	String passwd="1115101321";
	//�����ݺ�����
    //Vector rowData,columnName;
	//��ʼ��ct
	public SqlHelper()
	{
		try 
		{
			//1����������,�������ƣ��̶�д��
			Class.forName(dirVerName);
			//2���õ�����(ָ�����ӵ��ĸ�����Դ���û���������)
		    ct=DriverManager.getConnection(url,user,passwd);
		} 
		catch (Exception e) 
		{
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//System.out.println("sf");
	}
	
	  //�����ݿ������ɾ���� дһ������
		public boolean exeUpdate(String sql, String []paras)
		{
			boolean b=true;
			try {
				ps=ct.prepareStatement(sql);
				//��sql����е�?�Ÿ�ֵ
				for(int i=0; i<paras.length; i++)
				{
					ps.setString(i+1,paras[i]);
				}
				ps.executeUpdate();   //��������
			} catch (Exception e) {
				// TODO: handle exception
			}
			return b;
		}
	
	//����ֵ��sql��ѯ���Ĳ�ѯ��������β�ѯ����ְλ�����Է��ؽ���ĵ�һ�������ְλ
   public ResultSet query(String sql,String[] paras)
   {
	    try 
		{		
		    //3�����������
	        ps=ct.prepareStatement(sql);
	        //��SQL��丳ֵ
	        for(int i=0;i<paras.length;i++)
	        {
	        	//��ָ���ģ����и�ֵ��������1��ʼ����ֵparas�ĵ�0���ַ�
	        	ps.setString(i+1, paras[i]);
	        }
	        //4��ִ��
			//���ӡ�ɾ�����޸���executeUpdate(),��ѯ��executeQuery()
			rs=ps.executeQuery();
		} 
		catch (Exception e) 
		{
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}	
	   return rs;
   }
   //�ر���Դ����
   public void close()
   {
	   try 
		{
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();	
		} 
		catch (Exception e) 
		{
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
   }
  

//   //��ѯȫ�����ݿ�
//   public void queryAll(String sql)
//	{
//		//�м������
//				//��������6��
//				columnName=new Vector();
//				//������������
//				columnName.add("ѧ��");
//				columnName.add("����");
//				columnName.add("�Ա�");
//				columnName.add("����");
//				columnName.add("����");
//				columnName.add("ϵ��");
//				//������
//				rowData=new Vector();
//		try 
//		{
//			//3�����������
//			ps=ct.prepareStatement(sql);
//			//4��ִ��
//			//���ӡ�ɾ�����޸���executeUpdate(),��ѯ��executeQuery()
//			rs=ps.executeQuery();
//			//rs.next()����ָ��һ�У����ڵ�һ��ָ����У�����Ҫ��.next
//			//��û����ʱ������һ��false
//			while(rs.next())
//			{
//				//rowData���Դ�Ŷ�������
//				//�ѻ�õ�ÿһ�����ݼ��뵽hang��
//				//����֪�����ݿ��ÿһ�е���������
//				Vector hang=new Vector();
//				hang.add(rs.getString(1));
//				hang.add(rs.getString(2));
//				hang.add(rs.getString(3));
//				hang.add(rs.getInt(4));
//				hang.add(rs.getString(5));
//				hang.add(rs.getString(6));
//				//���뵽rowdata��
//				rowData.add(hang);
//			}
//		} 
//		catch (Exception e) 
//		{
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}
//		finally
//		{
//			try 
//			{
//				if(rs!=null) rs.close();
//				if(ps!=null) ps.close();
//				if(ct!=null) ct.close();	
//			} 
//			catch (Exception e) 
//			{
//				// TODO �Զ����ɵ� catch ��
//				e.printStackTrace();
//			}
//		}
//	}
   
//@Override
//public int getRowCount() {
//	// TODO �Զ����ɵķ������
//	//���ص�ǰһ���ж���������
//	return this.rowData.size();
//}
//
//@Override
//public int getColumnCount() {
//	// TODO �Զ����ɵķ������
//	//���ص�ǰһ���ж�����
//	return this.columnName.size();
//}
//
////��д����
//	@Override
//	public String getColumnName(int column) {
//		
//	// TODO �Զ����ɵķ������
//	//this.columnName.get(column);���ص���object��
//	//����Ҫǿ��ת����string
//	return (String) this.columnName.get(column);
//}
//
//@Override
//public Object getValueAt(int rowIndex, int columnIndex) {
//	// TODO �Զ����ɵķ������
//	//this.rowData.get(rowIndex)��������������ȡ��һ���е�ĳ������
//	return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
//}
}
