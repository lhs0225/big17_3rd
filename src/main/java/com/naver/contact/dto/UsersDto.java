package com.naver.contact.dto;

import java.sql.Date;

public class UsersDto {
	private String userid;
	private String userpw;
	private String username;
	private Date user_regdt;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getUser_regdt() {
		return user_regdt;
	}

	public void setUser_regdt(Date user_regdt) {
		this.user_regdt = user_regdt;
	}

	@Override
	public String toString() {
		return "[id=" + userid + ", pw=" + userpw + ", name=" + username + ", regdt="
				+ user_regdt + "]";
	}
}
