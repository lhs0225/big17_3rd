package com.naver.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.contact.dao.UserDao;
import com.naver.contact.dto.UsersDto;

import jakarta.servlet.http.HttpSession;

@Repository
public class UserService {
	
	@Autowired
    UserDao dao;
	
//	로그인 처리 메소드
	public String Login(String userid, String userpw
			, HttpSession session) {
		
//		id 존재 여부 메소드 호출
		int idcheck = dao.getIdCheck(userid);
//		불일치하면 바로 리턴
		if(idcheck == 0) {
			return "NO ID";
		}
		
		// id, 비번이 일치하는지 확인하는 메소드 호출
		int pwcheck = dao.getIdPassCheck(userid, userpw);
		if(pwcheck == 0) {
			return "NO PW";
		}
		
//		참일 경우 세션에 저장
//		세션 1시간 저장
		session.setMaxInactiveInterval(60*60);
//		아이디 및 회원명 세션에 저장
		String username = dao.getName(userid);
		session.setAttribute("userid", userid);
		session.setAttribute("username", username);
//		로그인 상태 세션에 저장
		session.setAttribute("islogin", "true");
		
		return "OK";
	} // end Login()
	
//	회원가입 메소드
	public String Signup(String userid, String userpw
			, String username) {
		UsersDto dto = new UsersDto();
//		id 중복여부 체크
		int idcheck = dao.getIdCheck(userid);
//		중복이면 "ID 중복" 리턴
		if(idcheck == 1) {
			return "IN USE";
		}
		
//		중복이 아니면 DB에 추가(회원가입)
		try {
			dto.setUserid(userid);
			dto.setUserpw(userpw);
			dto.setUsername(username);
			dao.addUser(dto);
//			예외 발생시 에러 반환
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
		
		return "OK";
	} // end Signup() 
}
