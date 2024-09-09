-- 메시지 운영쿼리
-- 1.리스트 - 메시지 받는 사람
select 
    m.no, m.content, m.senderID, sm.name, m.sendDate, 
    m.acceptID, am.name, m.acceptDate
from message m, member sm, member am
-- 조건은 우리가 보여주는 방법에 따라서 변경할 수 있다.
where m.senderID = sm.id and m.accepterID = am.id;

select * from message;
-- 2.메시지를 봤을때
-- acceptDate를 현재본 날짜로 수정
update message set acceptDate = sysDate where no = '1';

commit;