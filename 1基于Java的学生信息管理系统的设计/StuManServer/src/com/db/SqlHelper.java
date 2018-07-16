/*
 * 对数据库操作类
 * crud
 * 1、db类中的对象获得和数据库中的链接
 * 2、编写query函数，设置形参为SQL语句和模型类中的两个文本框中的数据
 * 3、并把查询到的结果都返回结果集
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
	//行数据和列名
    //Vector rowData,columnName;
	//初始化ct
	public SqlHelper()
	{
		try 
		{
			//1、加载驱动,驱动名称，固定写法
			Class.forName(dirVerName);
			//2、得到连接(指定连接到哪个数据源，用户名和密码)
		    ct=DriverManager.getConnection(url,user,passwd);
		} 
		catch (Exception e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//System.out.println("sf");
	}
	
	  //把数据库的曾，删，改 写一个函数
		public boolean exeUpdate(String sql, String []paras)
		{
			boolean b=true;
			try {
				ps=ct.prepareStatement(sql);
				//给sql语句中的?号赋值
				for(int i=0; i<paras.length; i++)
				{
					ps.setString(i+1,paras[i]);
				}
				ps.executeUpdate();   //更新数据
			} catch (Exception e) {
				// TODO: handle exception
			}
			return b;
		}
	
	//返回值是sql查询语句的查询结果，本次查询的是职位，所以返回结果的第一条语句是职位
   public ResultSet query(String sql,String[] paras)
   {
	    try 
		{		
		    //3、创建火箭车
	        ps=ct.prepareStatement(sql);
	        //对SQL语句赋值
	        for(int i=0;i<paras.length;i++)
	        {
	        	//对指定的？进行赋值，参数从1开始，赋值paras的第0个字符
	        	ps.setString(i+1, paras[i]);
	        }
	        //4、执行
			//增加、删除、修改用executeUpdate(),查询用executeQuery()
			rs=ps.executeQuery();
		} 
		catch (Exception e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
	   return rs;
   }
   //关闭资源方法
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
   }
  

//   //查询全部数据库
//   public void queryAll(String sql)
//	{
//		//中间的数据
//				//列名，共6个
//				columnName=new Vector();
//				//定义列名标题
//				columnName.add("学号");
//				columnName.add("名字");
//				columnName.add("性别");
//				columnName.add("年龄");
//				columnName.add("籍贯");
//				columnName.add("系别");
//				//行数据
//				rowData=new Vector();
//		try 
//		{
//			//3、创建火箭车
//			ps=ct.prepareStatement(sql);
//			//4、执行
//			//增加、删除、修改用executeUpdate(),查询用executeQuery()
//			rs=ps.executeQuery();
//			//rs.next()用于指向一列，由于第一次指向空列，所有要先.next
//			//当没有列时，返回一个false
//			while(rs.next())
//			{
//				//rowData可以存放多行数据
//				//把获得的每一行数据加入到hang中
//				//必须知道数据库的每一行的数据类型
//				Vector hang=new Vector();
//				hang.add(rs.getString(1));
//				hang.add(rs.getString(2));
//				hang.add(rs.getString(3));
//				hang.add(rs.getInt(4));
//				hang.add(rs.getString(5));
//				hang.add(rs.getString(6));
//				//加入到rowdata中
//				rowData.add(hang);
//			}
//		} 
//		catch (Exception e) 
//		{
//			// TODO 自动生成的 catch 块
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
//				// TODO 自动生成的 catch 块
//				e.printStackTrace();
//			}
//		}
//	}
   
//@Override
//public int getRowCount() {
//	// TODO 自动生成的方法存根
//	//返回当前一共有多少行数据
//	return this.rowData.size();
//}
//
//@Override
//public int getColumnCount() {
//	// TODO 自动生成的方法存根
//	//返回当前一共有多少列
//	return this.columnName.size();
//}
//
////重写列名
//	@Override
//	public String getColumnName(int column) {
//		
//	// TODO 自动生成的方法存根
//	//this.columnName.get(column);返回的是object，
//	//所有要强制转换成string
//	return (String) this.columnName.get(column);
//}
//
//@Override
//public Object getValueAt(int rowIndex, int columnIndex) {
//	// TODO 自动生成的方法存根
//	//this.rowData.get(rowIndex)本身是行向量，取出一行中的某列数据
//	return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
//}
}
