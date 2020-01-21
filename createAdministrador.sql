alter session set "_ORACLE_SCRIPT"=true;
CREATE USER administrador1
  IDENTIFIED BY 1234
  DEFAULT TABLESPACE biblioteca1
  QUOTA 20M on biblioteca1;
GRANT roleAdministrador TO administrador1;

