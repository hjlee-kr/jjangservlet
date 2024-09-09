-- ȸ����� �� ȸ��

-- 1.��ü����
-- FK�� �����Ǿ��ִ� ���̺��� ���� �����ϰ�, PK�� �����մϴ�.
drop table member CASCADE CONSTRAINTS PURGE;
drop table grade CASCADE CONSTRAINTS PURGE;

-- 2.��ü����
-- PK���ͻ���, FK �� PK������
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
    status varchar2(6) default '����',
    photo varchar2(100),
    newMsgCnt number default 0,
    -- FK ���� references�� �̿��ؼ� ����� �� ����: ���̺�(Į��)
    gradeNo number(1) default 1 references grade(gradeNo)
);

-- sample ������ �Է� : grade table
insert into grade values (1, '�Ϲ�ȸ��');
insert into grade values (9, '������');
commit;
select * from grade;

-- sample ������ �Է� : member table
insert into member (id, pw, name, gender, birth, email, gradeNo) 
values ('admin', 'admin', '������', '����', '1975-04-23', 'mukgabi@naver.com', 9);
insert into member (id, pw, name, gender, birth, email, gradeNo)  
values ('test1', 'test1', '�׽���1', '����', '2000-01-01', 'test1@naver.com', 1);

commit;
select * from member;
