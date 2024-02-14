/*
-- 회원정보 table 만들기
*/
create table users(		
		userid		varchar2(15) 	-- 로그인ID(pk)
	  , userpw		varchar2(15) 	-- 로그인PW
	  , username	varchar2(50)	-- 계정주인이름
	  , user_regdt	DATE			-- 회원가입일
);

ALTER TABLE USERS
ADD CONSTRAINTS pk_users_userid
PRIMARY KEY (userid);

/*
--전화번호 table 만들기
*/
CREATE TABLE contact(
	 	personid	number			-- seq (pk)
	  , name		varchar2(10)	-- 이름
	  , phone		VARCHAR2(11)	-- 전화번호		
	  , address 	varchar2(200)	-- 주소
   	  , gubunid		NUMBER			-- 그룹번호(fk) 그룹테이블 참조
   	  , regdt		DATE			-- 최초등록일
   	  , updatedt	DATE			-- 최종수정일
);

ALTER TABLE CONTACT 
ADD CONSTRAINTS pk_contact_personid
PRIMARY KEY (personid);

ALTER TABLE contact
ADD CONSTRAINTS fk_contact_gubuns_gubunid
FOREIGN KEY (gubunid)
REFERENCES gubuns (gubunid);

/* 
--users-contact 관계 table 만들기
--각 ID가 어떤 회원들을 등록했나?
*/
CREATE TABLE users_contact_join(
		userid		varchar2(15)	--(fk1) 로그인 테이블 아이디 참조
	  , personid	NUMBER			--(fk2) contact 테이블 cid(seq) 참조
);

ALTER TABLE users_contact_join
ADD CONSTRAINT pk_uc_join_userid
PRIMARY KEY (personid);

ALTER table users_contact_join
ADD CONSTRAINT fk_uc_join_userid
FOREIGN KEY (userid) 
REFERENCES users (userid);

ALTER table users_contact_join
ADD CONSTRAINT fk_uc_join_personid
FOREIGN KEY (personid) 
REFERENCES CONTACT (personid);

/*
--그룹 table 만들기
*/
create table gubuns(		
		gubunid number 			--그룹번호(pk)
	  , gubunname varchar2(30) 	--그룹이름
);

ALTER TABLE GUBUNS 
ADD CONSTRAINTS pk_gubuns_gubunid
PRIMARY KEY (gubunid);
