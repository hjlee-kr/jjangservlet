-- 공지사항 운영쿼리
-- 1. 리스트 (공지번호, 타이틀, 게시일, 종료일)
-- 1-1. 모든리스트 보여주는 경우
-- 날짜형식을 'yyyy-mm-dd'으로 보여주고 싶다.
select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
order by updateDate desc, no desc;

-- 1-2. 현재공지 리스트 쿼리
select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
 where startDate <= sysDate and sysDate <= endDate 
 order by updateDate desc, no desc;
 
-- 1-3. 지난공지 리스트 쿼리 (현재일이 종료일보다 뒤에있다)
select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
 where sysDate > endDate 
 order by updateDate desc, no desc;
 
-- 1-4. 예정공지 리스트 처리 (현재일이 시작일보다 앞에있다)
select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
 where sysDate < startDate 
 order by updateDate desc, no desc;
 
-- 공지쓰기
-- 공지수정
-- 공지삭제
-- 일반게시판 쿼리와 형식이 거의 같습니다.