-- 이미지게시판 스키마 (image)
-- 글번호, 제목, 내용, 작성자id, 작성일, 첨부파일명, 패스워드

-- 1. 제거
drop table image CASCADE CONSTRAINTS PURGE;
drop SEQUENCE image_seq;

-- 2. 생성
create table image (
    no number primary key, -- 글번호 -- 시퀀스로 자동입력
    title VARCHAR2(300) not null, -- 제목
    content VARCHAR2(2000) not null, -- 내용
    writerID VARCHAR2(20) REFERENCES member(id) not null, -- 이미지게시판은 회원만 사용가능
    writeDate date default sysDate,
    filename VARCHAR2(200) not null,
    pw varchar2(20) not null
);

create SEQUENCE image_seq;

-- 샘플데이터
insert into image (no, title, content, writerID, filename, pw) 
values (image_seq.nextval, '이미지', '이미지입니다',
'test1', '/upload/image/image.jpg', '1111');
commit;
select * from image;
