CREATE TABLESPACE biblioteca
  DATAFILE 'biblioteca.dat' 
  SIZE 20M
  AUTOEXTEND ON;
CREATE temporary TABLESPACE biblioteca_temp
  tempfile 'biblioteca_temp.dbf' 
  SIZE 10M
  AUTOEXTEND ON;
alter session set "_ORACLE_SCRIPT"=true;
CREATE USER usuario
  IDENTIFIED BY 1234
  DEFAULT TABLESPACE biblioteca
  TEMPORARY TABLESPACE biblioteca_temp
  QUOTA 20M on biblioteca;
GRANT create session TO usuario;
GRANT create table TO usuario;
GRANT create view TO usuario;
GRANT create any trigger TO usuario;
GRANT create any procedure TO usuario;
GRANT create sequence TO usuario;
GRANT create synonym TO usuario;