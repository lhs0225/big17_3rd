package com.naver.contact.dto;

import java.sql.Date;

public class ContactDto {
	private int personid;
	private String name;
	private String phone;
	private String address;
	private int gubunid;
	private String gubunname; // 화면 출력, 추가, 수정에 필요
	private Date regdt;
	private Date updatedt;

	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	public Date getRegdt() {
		return regdt;
	}

	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}

	public Date getUpdatedt() {
		return updatedt;
	}

	public void setUpdatedt(Date updatedt) {
		this.updatedt = updatedt;
	}

	@Override
	public String toString() {
		return "[id=" + personid + ", 이름=" + name + ", 전화번호=" + phone + ", 주소=" + address
				+ ", 그룹ID=" + gubunid + "]";
	}
}
