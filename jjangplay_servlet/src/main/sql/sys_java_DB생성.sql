-- java db를 만들고 user 'java'가 관리하도록 한다.
-- oracle 11g 버전에서는 user를 java로 만들면 db이름 java로 생성이 됩니다.
create user java identified by java;

-- user java 을 없는 명령은
drop user java;

-- user java db를 사용하기 위해 권한을 주어야 한다.
grant connect, resource, create view to java;