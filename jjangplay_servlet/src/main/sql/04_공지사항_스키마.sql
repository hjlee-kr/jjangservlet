-- 공지사항 스키마
-- 공지번호, 제목, 내용, 공지시작일, 공지종료일, 작성일, 수정일

-- 1. 객체제거
drop table notice CASCADE CONSTRAINTS PURGE;
drop SEQUENCE notice_seq;

-- 2. 객체생성
create table notice (
    no number primary key,
    title varchar2(300) not null,
    content varchar2(2000) not null,
    startDate date default sysDate,
    endDate date default '9999-12-31',
    writeDate date default sysDate,
    updateDate date default sysDate
);

-- 시퀀스 생성
create SEQUENCE notice_seq;


-- sample 데이터
-- 현재공지
insert into notice (no, title, content, startDate, endDate) 
values (notice_seq.nextval, '프로젝트기반 생성형 챗봇', '자바, 오라클, 프런트...', 
'2024-07-22', '2024-12-31');
-- 지난공지
insert into notice (no, title, content, startDate, endDate) 
values (notice_seq.nextval, '프로젝트기반 과정', '모집완료', 
'2024-07-22', '2024-07-31');
-- 예정공지
insert into notice (no, title, content, startDate, endDate) 
values (notice_seq.nextval, '프로젝트기반 과정 입니다.', '프런트 공부 할 예정입니다.', 
'2024-08-14', '2024-12-31');

select * from notice;