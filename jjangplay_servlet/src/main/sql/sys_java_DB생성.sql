-- java db�� ����� user 'java'�� �����ϵ��� �Ѵ�.
-- oracle 11g ���������� user�� java�� ����� db�̸� java�� ������ �˴ϴ�.
create user java identified by java;

-- user java �� ���� �����
drop user java;

-- user java db�� ����ϱ� ���� ������ �־�� �Ѵ�.
grant connect, resource, create view to java;