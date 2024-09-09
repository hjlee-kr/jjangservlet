-- 일반게시판 운영쿼리  : DML 명령어
-- 실습순서 : 1>>3>>1>>2>>4>>1>>2>>5>>1
-- CRUD (Create, Read, Update, Delete)

-- 1. 리스트 : 글번호, 타이틀, 작성자, 작성일, 조회수
-- 최근작성한 글이 상단에 올라오도록
select no, title, writer, writeDate, hit from board 
order by no desc; -- no칼럼을 기준으로 내림차순 정열
-- 오름차순 : asc, 안써도 기본값.

-- 2. 글보기 : 글번호, 제목, 내용, 작성자, 작성일, 조회수
-- 클릭한 글번호의 hit수를 1증가 시킨다. (조회수증가)
update board set hit = hit + 1 where no = 1;
commit; -- db에 적용
-- 1번글에 대한 것을 보여주는 명령
select no, title, content, writer, writeDate, hit from board 
where no = 1;

-- 3. 글쓰기 : 제목, 내용, 작성자, 비밀번호 입력 받아서 DB 저장
-- insert시 primary key값, not null로 지정한 값들이 있어야 한다.
-- primary key 값은 유일값으로 (중복안됨)
-- board(칼럼들) 값입력시 board안에 적은 칼럼순서대로 입력
insert into board(no, title, content, writer, pw) 
values (board_seq.nextval, '2교시', '일반게시판 운영쿼리 수업중입니다.', '이현진', '1111');
commit;

-- 4. 글수정 : 제목, 내용, 작성자를 수정할 수 있도록 만들예정입니다.
-- 수정시 글번호와 패스워드가 일치되어야 합니다.
update board set title='java', content='자바도 공부합니다.', writer='LEE' 
where no = 1 and pw = '1111';
commit;

-- 5. 글삭제
-- 조건 : 글번호, 비밀번호
delete from board where no = 1 and pw = '1111';
rollback;

-- 데이터를 자동으로 추가하고 싶을 때 ; 단 자료가 1개이상 있어야 한다.
insert into board (no, title, content, writer, pw) 
(select board_seq.nextval, title, content, writer, pw from board);

-- count(*) 저장된 리스트 수
-- as cnt로 하면 칼럼이름을 cnt로 변경하여 표시한다.
select count(*) as cnt from board;

-- 문자열 이어붙이는 방법
select (no || '.' || title) as data from board;

-- 검색기능을 사용하기 위해 사용하는 쿼리
select * from board where title like '%ja%' or content like '%ja%' 
or writer like '%ja%';