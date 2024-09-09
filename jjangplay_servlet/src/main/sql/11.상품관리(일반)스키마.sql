-- 상품관리 (일반적인사항) goods
-- 상품번호(PK), 상품명, 설명, 작성일, 제조일, 모델번호, 제조사, 이미지이름, 배송비

-- 1.삭제
drop table goods CASCADE CONSTRAINTS PURGE;
drop sequence goods_seq;

-- 2.생성
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