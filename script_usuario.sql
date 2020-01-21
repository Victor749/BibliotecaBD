CREATE TABLESPACE biblioteca3
  DATAFILE 'biblioteca3.dat' 
  SIZE 20M
  AUTOEXTEND ON;
CREATE temporary TABLESPACE biblioteca_temp3
  tempfile 'biblioteca_temp3.dbf' 
  SIZE 10M
  AUTOEXTEND ON;
alter session set "_ORACLE_SCRIPT"=true;
CREATE USER admin3
  IDENTIFIED BY 1234
  DEFAULT TABLESPACE biblioteca3
  TEMPORARY TABLESPACE biblioteca_temp3
  QUOTA 20M on biblioteca3;
GRANT create session TO admin3;
GRANT create table TO admin3;
GRANT create view TO admin3;
GRANT create any trigger TO admin3;
GRANT create any procedure TO admin3;
GRANT create sequence TO admin3;
GRANT create synonym TO admin3;
GRANT create public synonym TO admin3;
GRANT create user TO admin3;
GRANT create user TO admin3;
GRANT create role TO admin3;
GRANT ALL PRIVILEGES TO admin3 WITH ADMIN OPTION;




