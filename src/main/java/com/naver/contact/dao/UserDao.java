package com.naver.contact.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.naver.contact.dto.UsersDto;

@Repository
public class UserDao {	
//	jdbcTemplete
	private final JdbcTemplate jdbcTemplate;
	
	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

//	회원가입 메소드
	public void addUser(UsersDto dto) throws Exception {
		// 추가 쿼리 생성
		StringBuilder sb = new StringBuilder();
		
		sb.append("INSERT INTO USERS VALUES		");
		sb.append("(?, ?, ?, sysdate)			");
		
		String sql = sb.toString();
		
		// 추가 쿼리 실행
		try {
			jdbcTemplate.update(sql, dto.getUserid(), dto.getUserpw(), dto.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end addUser()
	
//	ID 존재 여부 체크 메소드
	public int getIdCheck(String userid) {
		StringBuilder sb = new StringBuilder();
		
//		id 존재 여부 조회
		sb.append("SELECT USERID		");
		sb.append("  FROM USERS 		");
		sb.append(" WHERE USERID IN ?	");
		
		String sql = sb.toString();
		String checkid = "";
		try {
			checkid = jdbcTemplate.queryForObject(sql, String.class, userid);
		} catch (Exception e) {
//			결과가 없으면 빈칸 그대로.
			checkid = "";
		}
		
//		id가 존재하면 리턴 1
		if(checkid.equals(userid)) {
			return 1;
//		존재하지 않으면 리턴 0
		} else {
			return 0;
		}
	} // end getIdCheck()

//	ID-비번 일치여부 체크 메소드
	public int getIdPassCheck(String userid, String userpw) {
		StringBuilder sb = new StringBuilder();
		
//		id에 해당하는 비번 조회
		sb.append("SELECT userpw		");
		sb.append("  FROM USERS			");
		sb.append(" WHERE userid = ?	");
		
		String sql = sb.toString();		
		String checkpw = "";
		try {
			checkpw = jdbcTemplate.queryForObject(sql, String.class, userid);
		} catch (Exception e) {
			e.printStackTrace();
			checkpw = "";
		}
		
//		id-pw가 일치하면 리턴 1
		if(checkpw.equals(userpw)) {
			return 1;
//		불일치하면 리턴 0
		} else {
			return 0;
		}
	} // end getIdPassCheck()

    public String getName(String userid) {
		StringBuilder sb = new StringBuilder();
		String username = "";
		
//		id에 해당하는 이름 조회
		sb.append("SELECT username		");
		sb.append("  FROM USERS			");
		sb.append(" WHERE userid = ?	");
		
		String sql = sb.toString();		

		try {
			username = jdbcTemplate.queryForObject(sql, String.class, userid);
		} catch (Exception e) {
			e.printStackTrace();
			username = "";
		}
    	
        return username;
    } // end getName()
}
