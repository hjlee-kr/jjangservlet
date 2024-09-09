-- ��ǰ���� (����) ��Ű��
-- ���ݳѹ�(PK), ��ǰ��ȣ(goods.gno, FK), ����, ���ΰ�, ������
-- �ǸŽ�����, �Ǹ�������

-- 1.����
drop table price CASCADE CONSTRAINTS PURGE;
drop SEQUENCE price_seq;

-- 2.����
create table price (
    pno number primary key,
    gno number REFERENCES goods(gno) on delete cascade not null,
    std_price number not null,
    discount number,
    rate number,
    startDate date default sysDate,
    endDate date default '9999-12-31'
);

create SEQUENCE price_seq;