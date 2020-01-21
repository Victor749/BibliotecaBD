alter session set "_ORACLE_SCRIPT"=true;
CREATE USER bibliotecario1
  IDENTIFIED BY 1234
  DEFAULT TABLESPACE biblioteca2
  QUOTA 20M on biblioteca2;
GRANT bibliotecario TO bibliotecario1;



