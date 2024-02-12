package com.naver.contact.dto;

public class UsersContactJoinDto {
	private String userid;
	private int personid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	@Override
	public String toString() {
		return "[userid=" + userid + ", personid=" + personid + "]";
	}
	
}
