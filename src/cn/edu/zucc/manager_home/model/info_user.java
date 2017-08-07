package cn.edu.zucc.manager_home.model;

import cn.edu.zucc.manager_home.model.info_user;

public class info_user {
	private String username;
	private String pwd;
	private String pwd2;
	public static  info_user currentLoginUser=null ;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwd2() {
		return pwd2;
	}
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}
	
}
