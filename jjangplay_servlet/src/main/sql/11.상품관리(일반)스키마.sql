-- ��ǰ���� (�Ϲ����λ���) goods
-- ��ǰ��ȣ(PK), ��ǰ��, ����, �ۼ���, ������, �𵨹�ȣ, ������, �̹����̸�, ��ۺ�

-- 1.����
drop table goods CASCADE CONSTRAINTS PURGE;
drop sequence goods_seq;

-- 2.����
create table goods (
    gno number primary key,
    name varchar2(300) not null,
    content varchar2(2000) not null,
    writeDate date default sysDate,
    productDate date,
    modelNo varchar2(20) not null,
    company varchar2(30) not null,
    imageName varchar2(200) not null,
    delivery_cost number not null
);

create SEQUENCE goods_seq;