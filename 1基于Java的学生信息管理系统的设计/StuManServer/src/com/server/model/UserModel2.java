/*
 * 用户表数据模型，用它完成对用户的各种操作,这里主要编写项目业务操作
 * */
package com.server.model;
import com.db.SqlHelper;

import java.sql.*;
public class UserModel2 
{
	/**
	 * 
	 * @param uid 用户编号
	 * @param p 用户密码
	 * @return 返回的是用户的职位，如果不存在返回null
	 */
	
	public String[] show(String stuId)
	{
		//因为设置查询语句查询的是职位
		   String info[] ={"","","","","","","",""};
		   SqlHelper sp=null;
		   try 
		   {
			   //组织SQL语句和参数列表
		       //String sql="select 人事资料.职位 from 登入,人事资料 where 登入.员工号=人事资料.员工号 and 登入.员工号=? and 登入.密码=?";
			   String sql="select * from stu where stuId=?";
			   String paras[]={stuId};
		       sp=new SqlHelper();
		       ResultSet rs=sp.query(sql, paras);
		       //System.out.println("jkl");
		       //如果有结果，也就是有匹配的员工号和密码的人，返回职位
		       ResultSetMetaData rsmt=rs.getMetaData();
			   if(rs.next())
			   {
				  //取出职位
				  //获取此 ResultSet对象的当前行中指定列的值
				  for(int i=0;i<8;i++)
				  {
					  info[i]=rs.getString(i+1);
				  }				  
			   }
		   } 
		   catch (SQLException e) 
		   {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		   }
		   finally
		   {
			   sp.close();
		   }
		   //System.out.println("sdf");
		   return info;
	}
	//用来接收从文本框中获取的员工号和密码，
	//与数据库中的记录比较，一致则返回该员工号的人的职位
	//是经理或主管，则进入管理界面
   public String checkUser(String uid,String p)
   {
	   //因为设置查询语句查询的是职位
	   String zhiwei="";
	   SqlHelper sp=null;
	   try 
	   {
		   //组织SQL语句和参数列表
	       //String sql="select 人事资料.职位 from 登入,人事资料 where 登入.员工号=人事资料.员工号 and 登入.员工号=? and 登入.密码=?";
		   String sql="select stu.stupos from stu where stuId=? and stups=?";
		   String paras[]={uid,p};
	       sp=new SqlHelper();
	       ResultSet rs=sp.query(sql, paras);
	       //System.out.println("jkl");
	       //如果有结果，也就是有匹配的员工号和密码的人，返回职位
		   if(rs.next())
		   {
			  //取出职位
			  //获取此 ResultSet对象的当前行中指定列的值
			  zhiwei=rs.getString(1);
			  
		   }
	   } 
	   catch (SQLException e) 
	   {
		// TODO 自动生成的 catch 块
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
