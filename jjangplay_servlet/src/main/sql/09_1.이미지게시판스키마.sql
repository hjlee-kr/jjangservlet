-- <�̹��� �Խ��� ��Ű��> --
-- ����
drop table image cascade CONSTRAINTS;
drop SEQUENCE image_seq;

-- ����
create table image(
    no number primary key, -- �۹�ȣ, image_seq�� ����
    title varchar2(300) not null, -- ����
    content varchar2(2000) not null, -- ����
    id varchar2(20) references member(id) not null, -- ȸ���� ��ϰ���
    writeDate date default sysdate, -- �ۼ���¥
    fileName varchar2(200) not null -- �̹��� ���� (��ġ+�����̸�)
);

-- image table no�� ����� ������
create SEQUENCE image_seq;

-- ���õ�����
insert into image(no, title, content, id, fileName) 
values(image_seq.nextval, '�����̹���', '�̹����Դϴ�', 'test1', '/upload/image/1.png');
commit;
