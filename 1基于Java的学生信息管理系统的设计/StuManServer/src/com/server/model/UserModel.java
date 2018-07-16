/*
 * 用户表数据模型，用它完成对用户的各种操作,这里主要编写项目业务操作
 * 1、model中的类主要编写函数，形参设置两个字符串变量（用来接收view传过来中文本框中的数据）
 * 2、在函数中设置SQL查询语句，用于用户的验证
 * 3、新建SqlHelper类对象，调用SqlHelper中的query函数，并接收返回来的结果集
 * */
package com.server.model;
import com.db.SqlHelper;

import java.sql.*;
public class UserModel 
{
	/**
	 * 
	 * @param uid 用户编号
	 * @param p 用户密码
	 * @return 返回的是用户的职位，如果不存在返回null
	 */
	
	
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
		   String sql="select teacher.teapos from teacher where teacher.teaId=? and teacher.Ps=?";
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
