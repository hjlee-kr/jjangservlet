-- �޽��� �����
-- 1.����Ʈ - �޽��� �޴� ���
select 
    m.no, m.content, m.senderID, sm.name, m.sendDate, 
    m.acceptID, am.name, m.acceptDate
from message m, member sm, member am
-- ������ �츮�� �����ִ� ����� ���� ������ �� �ִ�.
where m.senderID = sm.id and m.accepterID = am.id;

select * from message;
-- 2.�޽����� ������
-- acceptDate�� ���纻 ��¥�� ����
update message set acceptDate = sysDate where no = '1';

commit;