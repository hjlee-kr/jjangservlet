-- 상품관리 (가격) 스키마
-- 가격넘버(PK), 상품번호(goods.gno, FK), 정가, 할인가, 할인율
-- 판매시작일, 판매종료일

-- 1.삭제
drop table price CASCADE CONSTRAINTS PURGE;
drop SEQUENCE price_seq;

-- 2.생성
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