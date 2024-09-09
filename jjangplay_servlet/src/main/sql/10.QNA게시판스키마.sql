-- Q&A �Խ��� ��Ű��
-- �۹�ȣ, ����, ����, �ۼ���id, �ۼ���, ��ȸ��,
-- ���ñ۹�ȣ (������: �ڽ��Ǳ۹�ȣ, �亯: �����۹�ȣ)
-- ������ȣ (������: ���±�+1, �亯: Max(ordNo)+1)
-- �鿩�����ȣ (�亯�� : �亯��+1)
-- �θ�۹�ȣ (���� �����ִ� �۹�ȣ, qna.no)

-- 1.����
drop table qna CASCADE CONSTRAINTS PURGE;
drop SEQUENCE qna_seq;

-- 2.����
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
    -- on delete cascade: ������ �����ɶ� ������ �޸� �亯���� �� ������.
    parentNo number references qna(no) on delete cascade 
);

create SEQUENCE qna_seq;