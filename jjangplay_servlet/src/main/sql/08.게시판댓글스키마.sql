-- �ϹݰԽ��� ������
-- ��۵� �ϳ��� ���̺�� ������ �մϴ�.
-- �ϹݰԽ��� ��� ��Ű�� �ۼ�
-- ��۹�ȣ(PK), �۹�ȣ(��� �޸� �������?, FK, board.no),
-- ����, ������, �ۼ���, ��й�ȣ

-- 1. ��ü����
drop table board_reply CASCADE CONSTRAINTS PURGE;
drop SEQUENCE board_reply_seq;

-- 2. ��ü ����
create table board_reply (
    rno number primary key,
    no number references board(no) not null,
    content VARCHAR2(600) not null,
    writer varchar2(30) not null,
    writeDate date default sysDate,
    pw varchar2(20) not null
);

create SEQUENCE board_reply_seq;

-- ��� ���õ�����
-- ��ۿ��� �۳ѹ��� ���ϴ�.
select no from board;
insert into board_reply(rno, no, content, writer, pw) 
 values (board_reply_seq.nextval, 1, '�����ֽ��ϴ�.', 'ȫ�浿', '1111');

insert into board_reply(rno, no, content, writer, pw) 
 values (board_reply_seq.nextval, 2, '�ϹݰԽ��� ���.', '�̼���', '1111');
commit;
select * from board_reply;



