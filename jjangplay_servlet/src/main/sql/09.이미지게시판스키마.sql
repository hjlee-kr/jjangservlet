-- �̹����Խ��� ��Ű�� (image)
-- �۹�ȣ, ����, ����, �ۼ���id, �ۼ���, ÷�����ϸ�, �н�����

-- 1. ����
drop table image CASCADE CONSTRAINTS PURGE;
drop SEQUENCE image_seq;

-- 2. ����
create table image (
    no number primary key, -- �۹�ȣ -- �������� �ڵ��Է�
    title VARCHAR2(300) not null, -- ����
    content VARCHAR2(2000) not null, -- ����
    writerID VARCHAR2(20) REFERENCES member(id) not null, -- �̹����Խ����� ȸ���� ��밡��
    writeDate date default sysDate,
    filename VARCHAR2(200) not null,
    pw varchar2(20) not null
);

create SEQUENCE image_seq;

-- ���õ�����
insert into image (no, title, content, writerID, filename, pw) 
values (image_seq.nextval, '�̹���', '�̹����Դϴ�',
'test1', '/upload/image/image.jpg', '1111');
commit;
select * from image;
