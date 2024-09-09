-- <이미지 게시판 스키마> --
-- 제거
drop table image cascade CONSTRAINTS;
drop SEQUENCE image_seq;

-- 생성
create table image(
    no number primary key, -- 글번호, image_seq로 세팅
    title varchar2(300) not null, -- 제목
    content varchar2(2000) not null, -- 내용
    id varchar2(20) references member(id) not null, -- 회원만 등록가능
    writeDate date default sysdate, -- 작성날짜
    fileName varchar2(200) not null -- 이미지 파일 (위치+파일이름)
);

-- image table no에 사용할 시퀀스
create SEQUENCE image_seq;

-- 샘플데이터
insert into image(no, title, content, id, fileName) 
values(image_seq.nextval, '샘플이미지', '이미지입니다', 'test1', '/upload/image/1.png');
commit;
