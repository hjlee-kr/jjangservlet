-- 메시지 스키마
-- 1. 삭제
drop table message CASCADE CONSTRAINTS PURGE;
drop SEQUENCE message_seq;

-- 2. 객체생성 (메시지번호, 내용, 보낸사람ID, 보낸날짜, 받는사람ID, 받는날짜, 전체보냄)
create table message(
    no number primary key,
    content varchar2(2000) not null,
    senderID varchar2(20) references member(id) not null,
    sendDate date default sysDate,
    accepterID varchar2(20) references member(id),
    acceptDate date,
    allUser number(1) default 0
);

create SEQUENCE message_seq;

-- sample
insert into message(no, content, senderID, accepterID) 
values (message_seq.nextval, '감사합니다', 'admin', 'test1');

insert into message(no, content, senderID, accepterID) 
values (message_seq.nextval, '저도 감사합니다', 'test1', 'admin');
commit;

select * from message;









