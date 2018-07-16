package com.common;

public class Message implements java.io.Serializable{

	
	//信息种类
	private String mesType;
	//发送者
	private String sender;
	//接收者
	private String getter;
	//内容本身
	private String con;
	//发送时间
	private String sendTime;
	//验证的职位
	private String zhiwei;
	
	
	private String stuId;
	private String stuName;
	private String stuSex;
	private String stuAge ;
	private String stuJg;
	private String stuDept;
	private String stups;
	private String stupos;
	
	public String getStuId() {
	return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuAge() {
		return stuAge;
	}
	public void setStuAge(String stuAge) {
		this.stuAge = stuAge;
	}
	public String getStuJg() {
		return stuJg;
	}
	public void setStuJg(String stuJg) {
		this.stuJg = stuJg;
	}
	public String getStuDept() {
		return stuDept;
	}
	public void setStuDept(String stuDept) {
		this.stuDept = stuDept;
	}
	public String getStups() {
		return stups;
	}
	public void setStups(String stups) {
		this.stups = stups;
	}
	public String getStupos() {
		return stupos;
	}
	public void setStupos(String stupos) {
		this.stupos = stupos;
	}
	public String getZhiwei() {
		return zhiwei;
	}

	public void setZhiwei(String zhiwei) {
		this.zhiwei = zhiwei;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

}
