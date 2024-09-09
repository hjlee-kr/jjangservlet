--  카운트 세기
select * from board;
-- 1. 테이블 전체 카운드 (일반게시판)
select count(*) from board;
select count(no) from board;
-- 2. 작성자가 'LEE'인 데이터의 갯수
select count(*) from board where writer = 'LEE';
-- 3. 작성자별 데이터의 갯수
select writer, count(*) cnt from board group by writer;

select writer, count(*) cnt from board group by writer 
 having count(*) > 8 order by cnt desc;
 
-- 회원의 성별에 따르 인원수
select gender, count(*) cnt
 from member
 -- where 조건을 줄 수 있다.
 group by gender;
 -- having 조건을 줄 수 있다.
 -- order by 는 제일 뒤에