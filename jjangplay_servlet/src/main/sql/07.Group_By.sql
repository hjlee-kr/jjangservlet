--  ī��Ʈ ����
select * from board;
-- 1. ���̺� ��ü ī��� (�ϹݰԽ���)
select count(*) from board;
select count(no) from board;
-- 2. �ۼ��ڰ� 'LEE'�� �������� ����
select count(*) from board where writer = 'LEE';
-- 3. �ۼ��ں� �������� ����
select writer, count(*) cnt from board group by writer;

select writer, count(*) cnt from board group by writer 
 having count(*) > 8 order by cnt desc;
 
-- ȸ���� ������ ���� �ο���
select gender, count(*) cnt
 from member
 -- where ������ �� �� �ִ�.
 group by gender;
 -- having ������ �� �� �ִ�.
 -- order by �� ���� �ڿ�