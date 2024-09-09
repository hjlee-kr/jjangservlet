-- Q&A 게시판 스키마
-- 글번호, 제목, 내용, 작성자id, 작성일, 조회수,
-- 관련글번호 (질문글: 자신의글번호, 답변: 질문글번호)
-- 순서번호 (질문글: 보는글+1, 답변: Max(ordNo)+1)
-- 들여쓰기번호 (답변글 : 답변글+1)
-- 부모글번호 (현제 보고있는 글번호, qna.no)

-- 1.삭제
drop table qna CASCADE CONSTRAINTS PURGE;
drop SEQUENCE qna_seq;

-- 2.생성
create table qna (
    no number primary key,
    title varchar2(300) not null,
    content varchar2(2000) not null,
    id varchar2(20) references member(id) not null ,
    writeDate date default sysDate,
    hit number default 0,
    refNo number references qna(no),
    ordNo number,
    levNo number,
    -- on delete cascade: 질문이 삭제될때 질문에 달린 답변들을 다 지워라.
    parentNo number references qna(no) on delete cascade 
);

create SEQUENCE qna_seq;