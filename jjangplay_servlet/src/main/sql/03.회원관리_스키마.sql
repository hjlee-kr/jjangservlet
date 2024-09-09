-- 회원등급 및 회원

-- 1.객체제거
-- FK로 지정되어있는 테이블을 먼저 제거하고, PK를 제거합니다.
drop table member CASCADE CONSTRAINTS PURGE;
drop table grade CASCADE CONSTRAINTS PURGE;

-- 2.객체생성
-- PK부터생성, FK 는 PK생성후
create table grade (
    gradeNo number(1) primary key,
    gradeName VARCHAR2(21) not null
);

-- member table
create table member (
    id VARCHAR2(20) primary key,
    pw VARCHAR2(20) not null,
    name VARCHAR2(30) not null,
    gender varchar2(6) not null,
    birth date not null,
    tel varchar2(13),
    email varchar2(50) not null,
    regDate date default sysDate,
    conDate date default sysDate,
    status varchar2(6) default '정상',
    photo varchar2(100),
    newMsgCnt number default 0,
    -- FK 에서 references를 이용해서 연결될 값 지정: 테이블(칼럼)
    gradeNo number(1) default 1 references grade(gradeNo)
);

-- sample 데이터 입력 : grade table
insert into grade values (1, '일반회원');
insert into grade values (9, '관리자');
commit;
select * from grade;

-- sample 데이터 입력 : member table
insert into member (id, pw, name, gender, birth, email, gradeNo) 
values ('admin', 'admin', '관리자', '여자', '1975-04-23', 'mukgabi@naver.com', 9);
insert into member (id, pw, name, gender, birth, email, gradeNo)  
values ('test1', 'test1', '테스터1', '남자', '2000-01-01', 'test1@naver.com', 1);

commit;
select * from member;
