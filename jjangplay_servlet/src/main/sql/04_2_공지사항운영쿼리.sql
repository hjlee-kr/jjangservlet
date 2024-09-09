-- �������� �����
-- 1. ����Ʈ (������ȣ, Ÿ��Ʋ, �Խ���, ������)
-- 1-1. ��縮��Ʈ �����ִ� ���
-- ��¥������ 'yyyy-mm-dd'���� �����ְ� �ʹ�.
select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
order by updateDate desc, no desc;

-- 1-2. ������� ����Ʈ ����
select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
 where startDate <= sysDate and sysDate <= endDate 
 order by updateDate desc, no desc;
 
-- 1-3. �������� ����Ʈ ���� (�������� �����Ϻ��� �ڿ��ִ�)
select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
 where sysDate > endDate 
 order by updateDate desc, no desc;
 
-- 1-4. �������� ����Ʈ ó�� (�������� �����Ϻ��� �տ��ִ�)
select no, title,
 to_char(startDate, 'yyyy-mm-dd') as startDate,
 to_char(endDate, 'yyyy-mm-dd') as endDate
 from notice 
 where sysDate < startDate 
 order by updateDate desc, no desc;
 
-- ��������
-- ��������
-- ��������
-- �ϹݰԽ��� ������ ������ ���� �����ϴ�.