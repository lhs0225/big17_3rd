/* contact 테이블 */
-- 초기 데이터 대입
INSERT INTO CONTACT VALUES
		((SELECT nvl(max(PERSONID),0)+1 FROM CONTACT)
		, '김민수', '01012345678', '서울시 마포구 서교동', 1, sysdate, '');
INSERT INTO CONTACT VALUES
		((SELECT nvl(max(PERSONID),0)+1 FROM CONTACT)
		, '이수진', '01098765432', '경기도 성남시 분당구 야탑동', 2, sysdate, '');
INSERT INTO CONTACT VALUES
		((SELECT nvl(max(PERSONID),0)+1 FROM CONTACT)
		, '박영희', '01076543210', '부산광역시 해운대구 우동', 3, sysdate, '');
INSERT INTO CONTACT VALUES
		((SELECT nvl(max(PERSONID),0)+1 FROM CONTACT)
		, '최정현', '01054321098', '대구광역시 중구 동성로', 4, sysdate, '');
INSERT INTO CONTACT VALUES
		((SELECT nvl(max(PERSONID),0)+1 FROM CONTACT)
		, '윤혜원', '01032109876', '광주광역시 서구 상무지구', 1, sysdate, '');
INSERT INTO CONTACT VALUES
		((SELECT nvl(max(PERSONID),0)+1 FROM CONTACT)
		, '정지훈', '01011111111', '서울특별시 강남구 역삼동', 1, sysdate, '');

/* 구분 테이블 */
-- 초기데이터 대입
INSERT INTO gubuns VALUES ((SELECT nvl(max(GUBUNID),0)+1 FROM gubuns),'가족');
INSERT INTO gubuns VALUES ((SELECT nvl(max(GUBUNID),0)+1 FROM gubuns),'친구');
INSERT INTO gubuns VALUES ((SELECT nvl(max(GUBUNID),0)+1 FROM gubuns),'회사');
INSERT INTO gubuns VALUES ((SELECT nvl(max(GUBUNID),0)+1 FROM gubuns),'기타');