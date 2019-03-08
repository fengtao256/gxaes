package com.gxaes.es.entity;

import java.util.Date;

public class Student {
	private Integer stuId ;
	private String loginName;
	private String pwd ;
	private String email ;
	private String mobile ;
	private Date registerTime;
	
	public Student(){
		//无参数构造器
	}
	public Student(String loginName, String pwd, String email, String mobile) {
		super();
		this.loginName = loginName;
		this.pwd = pwd;
		this.email = email;
		this.mobile = mobile;
	}
	
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
