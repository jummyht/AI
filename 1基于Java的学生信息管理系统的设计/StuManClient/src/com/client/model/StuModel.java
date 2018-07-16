/*
 * ����stu��ģ��
 * �Թ���ϵͳ�ĸ��ֲ���������д������
 * */
package com.client.model;
import java.sql.*;
import java.util.Vector;

import javax.swing.table.*;
//�̳г����ģ��
public class StuModel extends AbstractTableModel
{
	//�����ݺ�����
    Vector rowData,columnName;	
	//����������ݿ���Ҫ�Ķ���
	//PreparedStatement�ô�����Ҫ���ڷ���SQL���
	PreparedStatement ps=null;
	//2���õ�����(ָ�����ӵ��ĸ�����Դ���û���������)
	Connection ct=null;
	//���ؽ��
	ResultSet rs=null;
	
	public void init(String sql)
	{
		if(sql=="")
		{
			//��ʾstu���е���������
			sql="select * from stu";
		}
		//�м������
		//��������6��
		columnName=new Vector();
		//������������
		columnName.add("ѧ��");
		columnName.add("����");
		columnName.add("�Ա�");
		columnName.add("����");
		columnName.add("����");
		columnName.add("ϵ��");
		//������
		rowData=new Vector();	
		try 
		{
			//1����������,�������ƣ��̶�д��
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2���õ�����(ָ�����ӵ��ĸ�����Դ���û���������)
			ct=DriverManager.getConnection
			("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Students2","jummyht","1115101321");
			//3�����������
			ps=ct.prepareStatement(sql);
			//4��ִ��
			//���ӡ�ɾ�����޸���executeUpdate(),��ѯ��executeQuery()
			rs=ps.executeQuery();
			//rs.next()����ָ��һ�У����ڵ�һ��ָ����У�����Ҫ��.next
			//��û����ʱ������һ��false
			while(rs.next())
			{
				//rowData���Դ�Ŷ�������
				//�ѻ�õ�ÿһ�����ݼ��뵽hang��
				//����֪�����ݿ��ÿһ�е���������
				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				//���뵽rowdata��
				rowData.add(hang);
			}
		} 
		catch (Exception e) 
		{
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		finally
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
	}
	
	public void addStu(String sql)
	{
		
	}
	
	//���¶��幹�캯����ͨ������SQL������������ģ��
	public StuModel(String sql)
	{
		this.init(sql);
	}
	//���캯�����������ǳ�ʼ���ҵ�����ģ��
	public StuModel()
	{
		this.init("");
	}
	

	//һ���Զ����ɵĺ������Զ�����
	//�õ����ж�����
	@Override
	public int getRowCount() 
	{
		// TODO �Զ����ɵķ������
		//���ص�ǰһ���ж���������
		return this.rowData.size();
	}

	//�õ����ж�����
	@Override
	public int getColumnCount() 
	{
		// TODO �Զ����ɵķ������
		//���ص�ǰһ���ж�����
		return this.columnName.size();
	}

	//��д����
	@Override
	public String getColumnName(int column) {
		
		// TODO �Զ����ɵķ������
		//this.columnName.get(column);���ص���object��
		//����Ҫǿ��ת����string
		return (String) this.columnName.get(column);
	}


	//�õ�ĳ��ĳ�е�����
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		// TODO �Զ����ɵķ������
		//this.rowData.get(rowIndex)��������������ȡ��һ���е�ĳ������
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}
	

}
