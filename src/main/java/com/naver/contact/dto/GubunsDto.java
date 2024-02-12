package com.naver.contact.dto;

public class GubunsDto {
	private int gubunid;
	private String gubunname;

	public int getGubunid() {
		return gubunid;
	}

	public void setGubunid(int gubunid) {
		this.gubunid = gubunid;
	}

	public String getGubunname() {
		return gubunname;
	}

	public void setGubunname(String gubunname) {
		this.gubunname = gubunname;
	}

	@Override
	public String toString() {
		return "[gubunid=" + gubunid + ", gubunname=" + gubunname + "]";
	}
}
