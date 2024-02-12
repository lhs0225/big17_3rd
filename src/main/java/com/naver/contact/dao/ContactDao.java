package com.naver.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.naver.contact.dto.ContactDto;
import com.naver.contact.dto.GubunsDto;


@Repository
public class ContactDao {	
//	jdbcTemplete
	private final JdbcTemplate jdbcTemplate;
	
	public ContactDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
//	연락처 추가 메소드
	public void addContact(ContactDto dto) throws Exception {
		// 연락처 테이블 추가 쿼리 생성
		StringBuilder sb = new StringBuilder();
		
		sb.append("INSERT INTO CONTACT VALUES						");
		sb.append("((SELECT nvl(max(PERSONID),0)+1 FROM CONTACT)	");
		sb.append(", ?, ?, ?, ?, sysdate, '')						");
		
		String sql = sb.toString();
		
		// 추가 쿼리 실행
		try {
			jdbcTemplate.update(sql, dto.getName(), dto.getPhone()
					, dto.getAddress(), dto.getGubunid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end addContact()
	
//	전체 목록 출력 메소드
	public ArrayList<ContactDto> getAllContact
		(ArrayList<Integer> idlist) throws Exception {
		ArrayList<ContactDto> conlist = new ArrayList<>();
		List<ContactDto> list;
		
		StringBuilder sb = new StringBuilder();
		
		// select 쿼리 생성
		sb.append("SELECT c.PERSONID 				");
		sb.append("     , c.NAME 					");
		sb.append("	    , c.PHONE 					");
		sb.append("     , c.ADDRESS  				");	
		sb.append("     , g.GUBUNNAME  				");
		sb.append("  FROM CONTACT c, 				");
		sb.append("		  GUBUNS g 					");
		sb.append("	WHERE c.GUBUNID = g.GUBUNID		");
		sb.append("   AND c.PERSONID = ?			");

		String sql = sb.toString();
		
		try {
			// select 쿼리 실행
			for(int i=0;i<idlist.size();i++) {
				// 연락처 시퀀스 추출
				int personid = idlist.get(i);
				// 쿼리 실행 및 리스트에 추가
				list = jdbcTemplate.query(sql, 
						new RowMapper<ContactDto>(){
							@Override
							public ContactDto mapRow(ResultSet rs, int rowNum) throws SQLException {
								ContactDto dto = new ContactDto();
								dto.setPersonid(rs.getInt("personid"));
								dto.setName(rs.getString("name"));
								dto.setPhone(rs.getString("phone"));
								dto.setAddress(rs.getString("address"));
								dto.setGubunname(rs.getString("gubunname"));
								return dto;
							}
					
				}
						, personid);
				conlist.addAll(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conlist;
	} // end getAllContact()
	
//	한명 연락처 출력 메소드
	public ContactDto getOneContact(int personid) throws Exception {
		ContactDto dto = new ContactDto();
		StringBuilder sb = new StringBuilder();
		
		// select 쿼리 생성
		sb.append("SELECT c.PERSONID				");
		sb.append("		, c.NAME					");
		sb.append("     , c.PHONE					");
		sb.append("     , c.ADDRESS					");
		sb.append("     , c.GUBUNID  				");	
		sb.append("     , g.GUBUNNAME 				");
		sb.append("  FROM CONTACT c,				");
		sb.append("       GUBUNS g					");
		sb.append(" WHERE c.GUBUNID = g.GUBUNID		");
		sb.append("   AND c.PERSONID = ?			");
		
		String sql = sb.toString();
		
		try {
			// select 쿼리 실행
			dto = jdbcTemplate.queryForObject(sql, 
					new RowMapper<ContactDto>(){
				@Override
				public ContactDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					ContactDto dto = new ContactDto();
					dto.setPersonid(rs.getInt("personid"));
					dto.setName(rs.getString("name"));
					dto.setPhone(rs.getString("phone"));
					dto.setAddress(rs.getString("address"));
					dto.setGubunid(rs.getInt("gubunid"));
					dto.setGubunname(rs.getString("gubunname"));
					return dto;
				}	
			}, personid);
			
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return dto;
		}
	} // end getOneContact()
	
//	연락처 수정 메소드
	public void updateContact(ContactDto dto) throws Exception {		
		// 연락처 테이블 수정 쿼리 생성
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE CONTACT				");
		sb.append("   SET NAME = ?				");
		sb.append("     , PHONE = ?				");
		sb.append("     , ADDRESS = ?			");
		sb.append("		, GUBUNID = ?			");
		sb.append("		, UPDATEDT = SYSDATE	");
		sb.append("WHERE PERSONID = ?			");
	 
		String sql = sb.toString();
		
		// 수정 쿼리 실행
		try {
			jdbcTemplate.update(sql, dto.getName(), dto.getPhone()
					, dto.getAddress(), dto.getGubunid(), dto.getPersonid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end editContact()
	
//	연락처 삭제 메소드
	public void delContact(int personid) throws Exception {
		// 연락처 테이블 삭제 쿼리 생성
		StringBuilder sb = new StringBuilder();
		
		sb.append("DELETE FROM CONTACT c 	");
		sb.append(" WHERE PERSONID = ? 		");
		
		String sql = sb.toString();
		
		// 삭제 쿼리 실행
		try {
			jdbcTemplate.update(sql, personid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end delContact()
	
//	그룹명 => 그룹ID 읽는 메소드
	public int getGubunId(String gubunname) throws Exception {
		int gubunid = 0;
		
//		select 쿼리 생성
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT GUBUNID 			");
		sb.append("  FROM GUBUNS 			");
		sb.append(" WHERE GUBUNNAME = ? 	");
		
		String sql = sb.toString();
		
//		쿼리 실행
		try {
			gubunid = (int)jdbcTemplate.queryForObject(sql, Integer.class, gubunname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gubunid;
	}
	
//	그룹 테이블 전체를 읽어오는 메소드
	public ArrayList<GubunsDto> getAllGubun() throws Exception {
		ArrayList<GubunsDto> gubunlist = new ArrayList<>();
		List<GubunsDto> list;
		
//		select 쿼리 생성
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM GUBUNS		");
		
		String sql = sb.toString();
		
//		select 쿼리 실행
		try {
			list = jdbcTemplate.query(sql, 
					new RowMapper<GubunsDto>(){
						@Override
						public GubunsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							GubunsDto dto = new GubunsDto();
							dto.setGubunid(rs.getInt("gubunid"));
							dto.setGubunname(rs.getString("gubunname"));
							return dto;
						}
			});
			gubunlist.addAll(list);
		} catch (Exception e) {
			e.printStackTrace();
			gubunlist.clear();
		}
		
		return gubunlist;
	}
}
