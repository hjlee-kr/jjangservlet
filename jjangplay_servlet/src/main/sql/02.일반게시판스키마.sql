-- �ϹݰԽ��� ���̺� ����(��Ű��) - DDL ��ɾ�
-- ��ü ����
drop table board cascade CONSTRAINTS PURGE;
drop SEQUENCE board_seq;

-- ��ü ����
create table board (
    -- �۹�ȣ
    -- number, number(2): �ڸ����� ���ڸ�, 
    -- number(5,2): �ڸ����� 5�ڸ�, �Ҽ���2�ڸ����� ���
    -- primary key : �ݵ�� ����ؾ��ϰ�, �ߺ��� ���� ���ȵ�
    no number primary key,
    -- ����
    -- varchar2(����) : ���� �ִ���̸� �ǹ��ϰ�,
    -- ���� ����ɶ� ���������� �޸� ������ �����մϴ�.
    -- not null : �Է½� ���� ����θ� �ȵȴ�.
    title varchar2(300) not null,
    -- �۳��� : �ѱ۷� 660�� ������ ����
    content varchar2(2000) not null,
    -- �ۼ���
    writer varchar2(30) not null,
    -- �ۼ���, date type�� ������� ������ �ִ� �ڷ���
    -- default��� ������ ���� �Է� �������� �⺻������ ���Ǵ� ��
    writeDate date default sysDate,
    -- ��ȸ��
    hit number default 0,
    -- �н�����, ���� ����, ������ ���
    pw varchar2(20) not null
);

-- board�� no�� ����� ������ ����
-- board�� no�� �ڵ�, ���������� �־��ݴϴ�.
create SEQUENCE board_seq;
