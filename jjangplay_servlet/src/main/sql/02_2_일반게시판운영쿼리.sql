-- �ϹݰԽ��� �����  : DML ��ɾ�
-- �ǽ����� : 1>>3>>1>>2>>4>>1>>2>>5>>1
-- CRUD (Create, Read, Update, Delete)

-- 1. ����Ʈ : �۹�ȣ, Ÿ��Ʋ, �ۼ���, �ۼ���, ��ȸ��
-- �ֱ��ۼ��� ���� ��ܿ� �ö������
select no, title, writer, writeDate, hit from board 
order by no desc; -- noĮ���� �������� �������� ����
-- �������� : asc, �Ƚᵵ �⺻��.

-- 2. �ۺ��� : �۹�ȣ, ����, ����, �ۼ���, �ۼ���, ��ȸ��
-- Ŭ���� �۹�ȣ�� hit���� 1���� ��Ų��. (��ȸ������)
update board set hit = hit + 1 where no = 1;
commit; -- db�� ����
-- 1���ۿ� ���� ���� �����ִ� ���
select no, title, content, writer, writeDate, hit from board 
where no = 1;

-- 3. �۾��� : ����, ����, �ۼ���, ��й�ȣ �Է� �޾Ƽ� DB ����
-- insert�� primary key��, not null�� ������ ������ �־�� �Ѵ�.
-- primary key ���� ���ϰ����� (�ߺ��ȵ�)
-- board(Į����) ���Է½� board�ȿ� ���� Į��������� �Է�
insert into board(no, title, content, writer, pw) 
values (board_seq.nextval, '2����', '�ϹݰԽ��� ����� �������Դϴ�.', '������', '1111');
commit;

-- 4. �ۼ��� : ����, ����, �ۼ��ڸ� ������ �� �ֵ��� ���鿹���Դϴ�.
-- ������ �۹�ȣ�� �н����尡 ��ġ�Ǿ�� �մϴ�.
update board set title='java', content='�ڹٵ� �����մϴ�.', writer='LEE' 
where no = 1 and pw = '1111';
commit;

-- 5. �ۻ���
-- ���� : �۹�ȣ, ��й�ȣ
delete from board where no = 1 and pw = '1111';
rollback;

-- �����͸� �ڵ����� �߰��ϰ� ���� �� ; �� �ڷᰡ 1���̻� �־�� �Ѵ�.
insert into board (no, title, content, writer, pw) 
(select board_seq.nextval, title, content, writer, pw from board);

-- count(*) ����� ����Ʈ ��
-- as cnt�� �ϸ� Į���̸��� cnt�� �����Ͽ� ǥ���Ѵ�.
select count(*) as cnt from board;

-- ���ڿ� �̾���̴� ���
select (no || '.' || title) as data from board;

-- �˻������ ����ϱ� ���� ����ϴ� ����
select * from board where title like '%ja%' or content like '%ja%' 
or writer like '%ja%';