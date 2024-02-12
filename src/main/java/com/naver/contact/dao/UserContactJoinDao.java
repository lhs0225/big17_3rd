package com.naver.contact.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.naver.contact.dto.UsersContactJoinDto;

@Repository
public class UserContactJoinDao {
	UsersContactJoinDto jdto;
	
//	jdbcTemplete
	private final JdbcTemplate jdbcTemplate;
	
	public UserContactJoinDao(JdbcTemplate jdbcTemplate)
		throws Exception {
		this.jdbcTemplate = jdbcTemplate;
	}
	
//	특정 유저가 추가한 연락처 목록 불러오는 메소드
	public ArrayList<Integer> getJoin(String userid) {
		ArrayList<Integer> idlist = new ArrayList<>();
		
		// select 쿼리 생성
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT PERSONID 				");
		sb.append("  FROM USERS_CONTACT_JOIN	");
		sb.append(" WHERE USERID = ?			");
		sb.append(" ORDER BY PERSONID			");
		
		String sql = sb.toString();
		
		// 쿼리 실행
		try {
			List<Integer> list = jdbcTemplate.queryForList(sql, Integer.class, userid);
			idlist.addAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return idlist;
	} // end getJoin()
	
//	유저-연락처 관계 추가 메소드
	public void addJoin(String userid) throws Exception {
		StringBuilder sb = new StringBuilder();
		String sql = "";
		
		try {
//			방금 추가한 사람의 personid 검색 쿼리 생성 및 실행
			sql = "SELECT nvl(max(PERSONID),0) FROM contact";
			int personid = (int)jdbcTemplate.queryForObject(sql, Integer.class);
			
//			userid와 personid를 테이블에 대입
			sb.append("INSERT INTO USERS_CONTACT_JOIN VALUES	");
			sb.append("(?, ?)									");
			
			sql = sb.toString();
			
			// 추가 쿼리 실행
			jdbcTemplate.update(sql, userid, personid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // end addJoin()
	
//	유저-연락처 관계 삭제 메소드
	public void delJoin(int personid) throws Exception {
		// 삭제 쿼리 생성
		StringBuilder sb = new StringBuilder();
		
		sb.append("DELETE FROM USERS_CONTACT_JOIN	");
		sb.append("WHERE PERSONID = ?				");
		
		String sql = sb.toString();
		
		// 삭제 쿼리 실행
		try {
			jdbcTemplate.update(sql, personid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end delJoin()
}
