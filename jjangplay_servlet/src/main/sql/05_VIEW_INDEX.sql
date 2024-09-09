-- ���������� �������, ��������, ���������� VIEW�� ����� ����
-- 1. ������� VIEW
create or replace view notice_pre 
as select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
 where startDate <= sysDate and sysDate <= endDate 
 order by updateDate desc, no desc;
 
-- �����������Ʈ ����ʹ�.
select no, title, startDate, endDate from notice_pre;

-- VIEW�� ���� ����
-- 1. ������ �����ϴ�.
-- 2. ��������� �ֱ� ���ؼ�

-- �˻��� ���ݴ� ������ �ϱ�����
-- index �� �����մϴ�.
create index board_no_idx on board(no desc);
-- ȸ�� �̸�, �������
create index board_name_birth_idx on member(name, birth);
