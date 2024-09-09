-- 공지사항의 현재공지, 지난공지, 예정공지를 VIEW로 만들어 보자
-- 1. 현재공지 VIEW
create or replace view notice_pre 
as select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
 where startDate <= sysDate and sysDate <= endDate 
 order by updateDate desc, no desc;
 
-- 현재공지리스트 보고싶다.
select no, title, startDate, endDate from notice_pre;

-- VIEW를 쓰는 이유
-- 1. 쿼리가 복잡하다.
-- 2. 사용제한을 주기 위해서

-- 검색을 조금더 빠르게 하기위해
-- index 를 제공합니다.
create index board_no_idx on board(no desc);
-- 회원 이름, 생년월일
create index board_name_birth_idx on member(name, birth);
