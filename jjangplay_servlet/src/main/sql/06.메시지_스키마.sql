-- �޽��� ��Ű��
-- 1. ����
drop table message CASCADE CONSTRAINTS PURGE;
drop SEQUENCE message_seq;

-- 2. ��ü���� (�޽�����ȣ, ����, �������ID, ������¥, �޴»��ID, �޴³�¥, ��ü����)
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
values (message_seq.nextval, '�����մϴ�', 'admin', 'test1');

insert into message(no, content, senderID, accepterID) 
values (message_seq.nextval, '���� �����մϴ�', 'test1', 'admin');
commit;

select * from message;









