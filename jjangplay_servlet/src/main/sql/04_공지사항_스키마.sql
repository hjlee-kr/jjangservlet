-- �������� ��Ű��
-- ������ȣ, ����, ����, ����������, ����������, �ۼ���, ������

-- 1. ��ü����
drop table notice CASCADE CONSTRAINTS PURGE;
drop SEQUENCE notice_seq;

-- 2. ��ü����
create table notice (
    no number primary key,
    title varchar2(300) not null,
    content varchar2(2000) not null,
    startDate date default sysDate,
    endDate date default '9999-12-31',
    writeDate date default sysDate,
    updateDate date default sysDate
);

-- ������ ����
create SEQUENCE notice_seq;


-- sample ������
-- �������
insert into notice (no, title, content, startDate, endDate) 
values (notice_seq.nextval, '������Ʈ��� ������ ê��', '�ڹ�, ����Ŭ, ����Ʈ...', 
'2024-07-22', '2024-12-31');
-- ��������
insert into notice (no, title, content, startDate, endDate) 
values (notice_seq.nextval, '������Ʈ��� ����', '�����Ϸ�', 
'2024-07-22', '2024-07-31');
-- ��������
insert into notice (no, title, content, startDate, endDate) 
values (notice_seq.nextval, '������Ʈ��� ���� �Դϴ�.', '����Ʈ ���� �� �����Դϴ�.', 
'2024-08-14', '2024-12-31');

select * from notice;