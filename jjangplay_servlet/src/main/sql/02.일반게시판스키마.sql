-- 일반게시판 테이블 구조(스키마) - DDL 명령어
-- 객체 제거
drop table board cascade CONSTRAINTS PURGE;
drop SEQUENCE board_seq;

-- 객체 생성
create table board (
    -- 글번호
    -- number, number(2): 자리수가 두자리, 
    -- number(5,2): 자리수가 5자리, 소수점2자리까지 사용
    -- primary key : 반드시 사용해야하고, 중복된 값이 허용안됨
    no number primary key,
    -- 제목
    -- varchar2(길이) : 길이 최대길이를 의미하고,
    -- 값이 저장될때 가변적으로 메모리 공간을 차지합니다.
    -- not null : 입력시 값을 비워두면 안된다.
    title varchar2(300) not null,
    -- 글내용 : 한글로 660자 정도의 공간
    content varchar2(2000) not null,
    -- 작성자
    writer varchar2(30) not null,
    -- 작성일, date type은 년월일을 가지고 있는 자료형
    -- default라고 쓴것은 값을 입력 안했을때 기본적으로 사용되는 값
    writeDate date default sysDate,
    -- 조회수
    hit number default 0,
    -- 패스워드, 글의 수정, 삭제시 사용
    pw varchar2(20) not null
);

-- board의 no에 사용할 시퀀스 생성
-- board의 no을 자동, 순서적으로 넣어줍니다.
create SEQUENCE board_seq;
