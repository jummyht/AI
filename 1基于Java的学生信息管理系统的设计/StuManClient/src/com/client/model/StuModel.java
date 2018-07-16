/*
 * 这是stu表模型
 * 对管理系统的各种操作都可以写到该类
 * */
package com.client.model;
import java.sql.*;
import java.util.Vector;

import javax.swing.table.*;
//继承抽象表模型
public class StuModel extends AbstractTableModel
{
	//行数据和列名
    Vector rowData,columnName;	
	//定义操作数据库需要的东西
	//PreparedStatement用处：主要用于发送SQL语句
	PreparedStatement ps=null;
	//2、得到连接(指定连接到哪个数据源，用户名和密码)
	Connection ct=null;
	//返回结果
	ResultSet rs=null;
	
	public void init(String sql)
	{
		if(sql=="")
		{
			//显示stu表中的所有数据
			sql="select * from stu";
		}
		//中间的数据
		//列名，共6个
		columnName=new Vector();
		//定义列名标题
		columnName.add("学号");
		columnName.add("名字");
		columnName.add("性别");
		columnName.add("年龄");
		columnName.add("籍贯");
		columnName.add("系别");
		//行数据
		rowData=new Vector();	
		try 
		{
			//1、加载驱动,驱动名称，固定写法
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2、得到连接(指定连接到哪个数据源，用户名和密码)
			ct=DriverManager.getConnection
			("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Students2","jummyht","1115101321");
			//3、创建火箭车
			ps=ct.prepareStatement(sql);
			//4、执行
			//增加、删除、修改用executeUpdate(),查询用executeQuery()
			rs=ps.executeQuery();
			//rs.next()用于指向一列，由于第一次指向空列，所有要先.next
			//当没有列时，返回一个false
			while(rs.next())
			{
				//rowData可以存放多行数据
				//把获得的每一行数据加入到hang中
				//必须知道数据库的每一行的数据类型
				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				//加入到rowdata中
				rowData.add(hang);
			}
		} 
		catch (Exception e) 
		{
			// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void addStu(String sql)
	{
		
	}
	
	//重新定义构造函数，通过传递SQL语句来获得数据模型
	public StuModel(String sql)
	{
		this.init(sql);
	}
	//构造函数，用于我们初始化我的数据模型
	public StuModel()
	{
		this.init("");
	}
	

	//一下自动生成的函数会自动调用
	//得到共有多少行
	@Override
	public int getRowCount() 
	{
		// TODO 自动生成的方法存根
		//返回当前一共有多少行数据
		return this.rowData.size();
	}

	//得到共有多少列
	@Override
	public int getColumnCount() 
	{
		// TODO 自动生成的方法存根
		//返回当前一共有多少列
		return this.columnName.size();
	}

	//重写列名
	@Override
	public String getColumnName(int column) {
		
		// TODO 自动生成的方法存根
		//this.columnName.get(column);返回的是object，
		//所有要强制转换成string
		return (String) this.columnName.get(column);
	}


	//得到某行某列的数据
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		// TODO 自动生成的方法存根
		//this.rowData.get(rowIndex)本身是行向量，取出一行中的某列数据
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}
	

}
