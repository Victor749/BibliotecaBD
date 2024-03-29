CREATE TABLE USUARIO
(
  CEDULA VARCHAR2(10) NOT NULL,
  NOMBRE VARCHAR2(50) NOT NULL,
  DIRECCION VARCHAR2(100),
  PUEDE_PRESTAMO NUMBER(1) CHECK (PUEDE_PRESTAMO IN (0,1)) NOT NULL,
  VETADO NUMBER(1) CHECK (VETADO IN (0,1)) NOT NULL,
  ULTIMA_FECHA_HORA_DEVOLUCION VARCHAR2(20),
  CONSTRAINT USUARIO_PK PRIMARY KEY (CEDULA)
);
INSERT INTO USUARIO (CEDULA, NOMBRE, DIRECCION, PUEDE_PRESTAMO, VETADO, ULTIMA_FECHA_HORA_DEVOLUCION) VALUES ('0105502843', 'AZAR JAVED', 'GUARIDA SALAMANDRA', 1, 0, ' ');
INSERT INTO USUARIO (CEDULA, NOMBRE, DIRECCION, PUEDE_PRESTAMO, VETADO, ULTIMA_FECHA_HORA_DEVOLUCION) VALUES ('0101676401', 'FRINGILLA DE VIGO', 'CAPITAL DEL IMPERIO NILFGAARDIANO', 1, 0, ' ');
INSERT INTO USUARIO (CEDULA, NOMBRE, DIRECCION, PUEDE_PRESTAMO, VETADO, ULTIMA_FECHA_HORA_DEVOLUCION) VALUES ('0987654321', 'BORCH TRES GRAJOS', 'MONTA�AS DE ZERRIKANIA', 1, 0, ' ');
INSERT INTO USUARIO (CEDULA, NOMBRE, DIRECCION, PUEDE_PRESTAMO, VETADO, ULTIMA_FECHA_HORA_DEVOLUCION) VALUES ('9876543210', 'TRISS MERIGOLD', 'TORRE DE MARIBOR', 1, 0, ' ');
CREATE TABLE AUTOR
(
  ID NUMBER NOT NULL,
  NOMBRE VARCHAR2(50) NOT NULL,
  CONSTRAINT AUTOR_PK PRIMARY KEY (ID)
);
INSERT INTO AUTOR (ID, NOMBRE) VALUES (1, 'AN�NIMO');
INSERT INTO AUTOR (ID, NOMBRE) VALUES (2, 'JASKIER');
INSERT INTO AUTOR (ID, NOMBRE) VALUES (3, 'VESEMIR');
INSERT INTO AUTOR (ID, NOMBRE) VALUES (4, 'NENNEKE');
INSERT INTO AUTOR (ID, NOMBRE) VALUES (5, 'REGIS');
CREATE TABLE EDITORIAL
(
  ID NUMBER NOT NULL,
  NOMBRE VARCHAR2(100) NOT NULL,
  CONSTRAINT EDITORIAL_PK PRIMARY KEY (ID)
);
INSERT INTO EDITORIAL (ID, NOMBRE) VALUES (1, 'DESCONOCIDA');
INSERT INTO EDITORIAL (ID, NOMBRE) VALUES (2, 'UNIVERSIDAD DE OXENFURT');
INSERT INTO EDITORIAL (ID, NOMBRE) VALUES (3, 'KAER MORHEN');
INSERT INTO EDITORIAL (ID, NOMBRE) VALUES (4, 'SANTUARIO DE MELITELE');
INSERT INTO EDITORIAL (ID, NOMBRE) VALUES (5, 'EDITORIAL DEL DUCADO DE TOUISSANT');
CREATE TABLE LIBRO
(
  ID NUMBER NOT NULL,
  TITULO VARCHAR2(100) NOT NULL,
  AUTOR_ID NUMBER NOT NULL,
  BAJA_DISPONIBILIDAD NUMBER(1) CHECK (BAJA_DISPONIBILIDAD IN (0,1)) NOT NULL,
  EJEMPLARES_TOTALES NUMBER NOT NULL,
  EJEMPLARES_PRESTADOS NUMBER NOT NULL,
  EJEMPLARES_DANIADOS NUMBER NOT NULL,
  CONSTRAINT LIBRO_PK PRIMARY KEY (ID),
  CONSTRAINT FK_AUTOR FOREIGN KEY (AUTOR_ID) REFERENCES AUTOR(ID)
);
INSERT INTO LIBRO (ID, TITULO, AUTOR_ID, BAJA_DISPONIBILIDAD, EJEMPLARES_TOTALES, EJEMPLARES_PRESTADOS, EJEMPLARES_DANIADOS) VALUES (1, 'VAMPIROS: HECHOS Y MITOS', 5, 0, 4, 2, 0);
INSERT INTO LIBRO (ID, TITULO, AUTOR_ID, BAJA_DISPONIBILIDAD, EJEMPLARES_TOTALES, EJEMPLARES_PRESTADOS, EJEMPLARES_DANIADOS) VALUES (2, 'ESPECTROS, APARICIONES Y CONDENADOS', 3, 0, 2, 0, 0);
INSERT INTO LIBRO (ID, TITULO, AUTOR_ID, BAJA_DISPONIBILIDAD, EJEMPLARES_TOTALES, EJEMPLARES_PRESTADOS, EJEMPLARES_DANIADOS) VALUES (3, 'PLANTAS DEL CAMPO', 4, 0, 2, 0, 0);
INSERT INTO LIBRO (ID, TITULO, AUTOR_ID, BAJA_DISPONIBILIDAD, EJEMPLARES_TOTALES, EJEMPLARES_PRESTADOS, EJEMPLARES_DANIADOS) VALUES (4, 'MONSTRUOS DEL PANTANO', 3, 0, 1, 0, 0);
INSERT INTO LIBRO (ID, TITULO, AUTOR_ID, BAJA_DISPONIBILIDAD, EJEMPLARES_TOTALES, EJEMPLARES_PRESTADOS, EJEMPLARES_DANIADOS) VALUES (5, 'S�TIRA A LA PROFEC�A DE ITHLINNE', 1, 0, 2, 0, 0);
INSERT INTO LIBRO (ID, TITULO, AUTOR_ID, BAJA_DISPONIBILIDAD, EJEMPLARES_TOTALES, EJEMPLARES_PRESTADOS, EJEMPLARES_DANIADOS) VALUES (6, 'BALADA DEL LOBO BLANCO', 2, 0, 1, 0, 0);
INSERT INTO LIBRO (ID, TITULO, AUTOR_ID, BAJA_DISPONIBILIDAD, EJEMPLARES_TOTALES, EJEMPLARES_PRESTADOS, EJEMPLARES_DANIADOS) VALUES (7, 'MEDICINA FORENSE', 4, 0, 2, 0, 0);
CREATE TABLE EDICION
(
  ISBN VARCHAR2(13) NOT NULL,
  LIBRO_ID NUMBER NOT NULL,
  EDITORIAL_ID NUMBER NOT NULL,
  NUMERO NUMBER(2) NOT NULL,
  FECHA VARCHAR2(10),
  DESCRIPCION VARCHAR2(200),
  CONSTRAINT EDICION_PK PRIMARY KEY (ISBN),
  CONSTRAINT FK_LIBRO FOREIGN KEY (LIBRO_ID) REFERENCES LIBRO(ID),
  CONSTRAINT FK_EDITORIAL FOREIGN KEY (EDITORIAL_ID) REFERENCES EDITORIAL(ID)
);
INSERT INTO EDICION (ISBN, LIBRO_ID, EDITORIAL_ID, NUMERO, FECHA, DESCRIPCION) VALUES ('1111111111111', 1, 1, 1, '1112-04-01', 'PERGAMINOS, LENGUA ANTIGUA');
INSERT INTO EDICION (ISBN, LIBRO_ID, EDITORIAL_ID, NUMERO, FECHA, DESCRIPCION) VALUES ('2222222222222', 1, 5, 2, '1216-07-02', 'ENCUADERNADO, TRADUCIDO A LENGUA COM�N, INCLUYE 8 NUEVOS MITOS');
INSERT INTO EDICION (ISBN, LIBRO_ID, EDITORIAL_ID, NUMERO, FECHA, DESCRIPCION) VALUES ('3333333333333', 5, 1, 1, '1019-11-22', 'PERGAMINOS, LENGUA �LFICA');
INSERT INTO EDICION (ISBN, LIBRO_ID, EDITORIAL_ID, NUMERO, FECHA, DESCRIPCION) VALUES ('4444444444444', 2, 3, 1, '1212-04-06', 'ENCUADERNADO, LENGUA COM�N');
INSERT INTO EDICION (ISBN, LIBRO_ID, EDITORIAL_ID, NUMERO, FECHA, DESCRIPCION) VALUES ('5555555555555', 3, 4, 1, '1213-05-07', 'ENCUADERNADO, LENGUA COM�N');
INSERT INTO EDICION (ISBN, LIBRO_ID, EDITORIAL_ID, NUMERO, FECHA, DESCRIPCION) VALUES ('6666666666666', 4, 3, 1, '1219-09-12', 'ENCUADERNADO, LENGUA COM�N');
INSERT INTO EDICION (ISBN, LIBRO_ID, EDITORIAL_ID, NUMERO, FECHA, DESCRIPCION) VALUES ('7777777777777', 6, 2, 1, '1222-12-11', 'ENCUADERNADO, LENGUA COM�N');
INSERT INTO EDICION (ISBN, LIBRO_ID, EDITORIAL_ID, NUMERO, FECHA, DESCRIPCION) VALUES ('8888888888888', 7, 4, 1, '1211-12-08', 'ENCUADERNADO, LENGUA ANTIGUA');
INSERT INTO EDICION (ISBN, LIBRO_ID, EDITORIAL_ID, NUMERO, FECHA, DESCRIPCION) VALUES ('9999999999999', 7, 4, 2, '1223-11-09', 'ENCUADERNADO, TRADUCIDO A LENGUA COM�N');
CREATE TABLE PLANTA
(
  ID NUMBER NOT NULL,
  NOMBRE VARCHAR2(50) NOT NULL,
  CONSTRAINT PLANTA_PK PRIMARY KEY (ID)
);
INSERT INTO PLANTA (ID, NOMBRE) VALUES (1, 'PLANTA BAJA');
INSERT INTO PLANTA (ID, NOMBRE) VALUES (2, '1ER PISO');
INSERT INTO PLANTA (ID, NOMBRE) VALUES (3, '2DO PISO');
CREATE TABLE ESTANTE
(
  PLANTA_ID NUMBER NOT NULL,
  ID NUMBER NOT NULL,
  NOMBRE VARCHAR2(50) NOT NULL,
  CONSTRAINT ESTANTE_PK PRIMARY KEY (PLANTA_ID, ID),
  CONSTRAINT FK_PLANTA FOREIGN KEY (PLANTA_ID) REFERENCES PLANTA(ID)
);
INSERT INTO ESTANTE (PLANTA_ID, ID, NOMBRE) VALUES (1, 1, 'ESTANTE A');
INSERT INTO ESTANTE (PLANTA_ID, ID, NOMBRE) VALUES (1, 2, 'ESTANTE B');
INSERT INTO ESTANTE (PLANTA_ID, ID, NOMBRE) VALUES (2, 1, 'ESTANTE C');
INSERT INTO ESTANTE (PLANTA_ID, ID, NOMBRE) VALUES (3, 1, 'ESTANTE D');
CREATE TABLE EJEMPLAR
(
  EDICION_ISBN VARCHAR2(13) NOT NULL,
  ID NUMBER NOT NULL,
  PLANTA_ID NUMBER NOT NULL,
  ESTANTE_ID NUMBER NOT NULL,
  PRESTADO NUMBER(1) CHECK (PRESTADO IN (0,1)) NOT NULL,
  OBSERVACIONES VARCHAR2(200),
  MAL_ESTADO NUMBER(1) CHECK (MAL_ESTADO IN (0,1)) NOT NULL,
  CONSTRAINT EJEMPLAR_PK PRIMARY KEY (EDICION_ISBN, ID),
  CONSTRAINT FK_EDICION FOREIGN KEY (EDICION_ISBN) REFERENCES EDICION(ISBN),
  CONSTRAINT FK_PLANTA_ESTANTE FOREIGN KEY (PLANTA_ID, ESTANTE_ID) REFERENCES ESTANTE(PLANTA_ID, ID)
);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('1111111111111', 1, 0, 1, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('1111111111111', 2, 0, 1, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('2222222222222', 1, 0, 1, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('2222222222222', 2, 0, 1, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('3333333333333', 1, 0, 1, 2, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('3333333333333', 2, 0, 1, 2, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('4444444444444', 1, 0, 1, 2, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('4444444444444', 2, 0, 2, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('5555555555555', 1, 0, 2, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('5555555555555', 2, 0, 2, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('6666666666666', 1, 0, 2, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('7777777777777', 1, 0, 3, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('8888888888888', 1, 0, 3, 1, ' ', 0);
INSERT INTO EJEMPLAR (EDICION_ISBN, ID, PRESTADO, PLANTA_ID, ESTANTE_ID, OBSERVACIONES, MAL_ESTADO) VALUES ('9999999999999', 1, 0, 3, 1, ' ', 0);
CREATE TABLE ALQUILER
(
  USUARIO_CEDULA VARCHAR2(100) NOT NULL,
  EDICION_ISBN VARCHAR2(13) NOT NULL,
  EJEMPLAR_ID NUMBER NOT NULL,
  FECHA_HORA_PRESTAMO VARCHAR2(20) NOT NULL,
  FECHA_HORA_ESTIMADA_ENTREGA VARCHAR2(20) NOT NULL,
  FECHA_HORA_ENTREGA VARCHAR2(20),
  CONSTRAINT ALQUILER_PK PRIMARY KEY (USUARIO_CEDULA , EDICION_ISBN, EJEMPLAR_ID, FECHA_HORA_PRESTAMO),
  CONSTRAINT FK_USUARIO FOREIGN KEY (USUARIO_CEDULA) REFERENCES USUARIO(CEDULA) ON DELETE CASCADE,
  CONSTRAINT FK_EJEMPLAR FOREIGN KEY (EDICION_ISBN, EJEMPLAR_ID) REFERENCES EJEMPLAR(EDICION_ISBN, ID) ON DELETE CASCADE
);
CREATE TABLE DESABASTECIMIENTO
(
  LIBRO_ID NUMBER NOT NULL,
  FECHA_HORA VARCHAR2(20) NOT NULL,
  CONSTRAINT DESABASTECIMIENTO_PK PRIMARY KEY (LIBRO_ID, FECHA_HORA),
  CONSTRAINT FK_LIBRO_ID FOREIGN KEY (LIBRO_ID) REFERENCES LIBRO(ID)
);
CREATE TABLE PEDIDO
(
  LIBRO_NOMBRE VARCHAR2(100) NOT NULL,
  FECHA_HORA VARCHAR2(20) NOT NULL,
  CONSTRAINT PEDIDO_PK PRIMARY KEY (LIBRO_NOMBRE, FECHA_HORA)
);
CREATE TABLE MENSAJE_PENDIENTE
(
  NOMBRE_USUARIO VARCHAR2(100) NOT NULL,
  MENSAJE VARCHAR2(200) NOT NULL,
  CONSTRAINT MENSAJE_PENDIENTE_PK PRIMARY KEY (NOMBRE_USUARIO)
);
/
CREATE TABLE NOMBRE
(
  NOMBRE_USER VARCHAR2(100) NOT NULL,
  CONSTRAINT NOMBRE_USUARIO_PK PRIMARY KEY (NOMBRE_USER)
);
-- INSERT INTO ALQUILER(USUARIO_CEDULA, EDICION_ISBN, EJEMPLAR_ID, FECHA_HORA_PRESTAMO, FECHA_HORA_ESTIMADA_ENTREGA) VALUES ('0123456789', '1111111111111', 1, '2019-12-27 09:10:02', '2019-12-28 00:00:00');
-- UPDATE EJEMPLAR SET PRESTADO = 1 WHERE EDICION_ISBN = '1111111111111' AND ID = 1;
-- INSERT INTO ALQUILER(USUARIO_CEDULA, EDICION_ISBN, EJEMPLAR_ID, FECHA_HORA_PRESTAMO, FECHA_HORA_ESTIMADA_ENTREGA) VALUES ('0123456789', '2222222222222', 2, '2019-12-27 09:10:02', '2019-12-28 00:00:00');
-- UPDATE EJEMPLAR SET PRESTADO = 1 WHERE EDICION_ISBN = '2222222222222' AND ID = 2;
-- UPDATE USUARIO SET PUEDE_PRESTAMO = 0 WHERE CEDULA = '0123456789';
-- INSERT INTO ALQUILER(USUARIO_CEDULA, EDICION_ISBN, EJEMPLAR_ID, FECHA_HORA_PRESTAMO, FECHA_HORA_ESTIMADA_ENTREGA) VALUES ('0987654321', '7777777777777', 1, '2019-12-27 11:00:23', '2019-12-28 12:30:00');
-- UPDATE EJEMPLAR SET PRESTADO = 1 WHERE EDICION_ISBN = '7777777777777' AND ID = 1;
-- UPDATE USUARIO SET PUEDE_PRESTAMO = 0 WHERE CEDULA = '0987654321';
-- UPDATE ALQUILER SET FECHA_HORA_ENTREGA = '2019-12-28 11:30:00' WHERE USUARIO_CEDULA = '0987654321' AND FECHA_HORA_PRESTAMO = '2019-12-27 11:00:23';
-- UPDATE USUARIO SET PUEDE_PRESTAMO = 1 WHERE CEDULA = '0987654321';
-- UPDATE EJEMPLAR SET PRESTADO = 0 WHERE EDICION_ISBN = '7777777777777' AND ID = 1;
COMMIT;

-- CREATE PUBLIC SYNONYM user_tables FOR admin3.user_tables;

CREATE PUBLIC SYNONYM USUARIO FOR ADMIN3.USUARIO;
CREATE PUBLIC SYNONYM ALQUILER FOR ADMIN3.ALQUILER;
CREATE PUBLIC SYNONYM AUTOR FOR ADMIN3.AUTOR;
CREATE PUBLIC SYNONYM EDICION FOR ADMIN3.EDICION;
CREATE PUBLIC SYNONYM EDITORIAL FOR ADMIN3.EDITORIAL;
CREATE PUBLIC SYNONYM EJEMPLAR FOR ADMIN3.EJEMPLAR;
CREATE PUBLIC SYNONYM ESTANTE FOR ADMIN3.ESTANTE;
CREATE PUBLIC SYNONYM LIBRO FOR ADMIN3.LIBRO;
CREATE PUBLIC SYNONYM PLANTA FOR ADMIN3.PLANTA;
CREATE PUBLIC SYNONYM PEDIDO FOR ADMIN3.PEDIDO;
CREATE PUBLIC SYNONYM NOMBRE FOR ADMIN3.NOMBRE;
CREATE PUBLIC SYNONYM USER_TABLES FOR sys.USER_TABLES;
CREATE PUBLIC SYNONYM DESABASTECIMIENTO FOR ADMIN3.DESABASTECIMIENTO;
CREATE PUBLIC SYNONYM MENSAJE_PENDIENTE FOR ADMIN3.MENSAJE_PENDIENTE;

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

CREATE ROLE BIBLIOTECARIO;
GRANT ALL ON USUARIO TO BIBLIOTECARIO;
GRANT ALL ON ALQUILER TO BIBLIOTECARIO;
GRANT SELECT ON AUTOR TO BIBLIOTECARIO;
GRANT SELECT ON EDICION TO BIBLIOTECARIO;
GRANT SELECT ON EDITORIAL TO BIBLIOTECARIO;
GRANT ALL ON EJEMPLAR TO BIBLIOTECARIO;
GRANT SELECT ON ESTANTE TO BIBLIOTECARIO;
GRANT SELECT ON LIBRO TO BIBLIOTECARIO;
GRANT SELECT ON PLANTA TO BIBLIOTECARIO;
GRANT SELECT ON DESABASTECIMIENTO TO BIBLIOTECARIO;
GRANT SELECT ON PEDIDO TO BIBLIOTECARIO;
GRANT SELECT ON MENSAJE_PENDIENTE TO BIBLIOTECARIO;
GRANT CREATE SESSION TO BIBLIOTECARIO;
CREATE PUBLIC SYNONYM BIBLIOTECARIO FOR BIBLIOTECARIO;



CREATE ROLE ADMINISTRADOR;
GRANT ALL ON USUARIO TO ADMINISTRADOR;
GRANT ALL ON ALQUILER TO ADMINISTRADOR;
GRANT ALL ON AUTOR TO ADMINISTRADOR;
GRANT ALL ON EDICION TO ADMINISTRADOR;
GRANT ALL ON EDITORIAL TO ADMINISTRADOR;
GRANT ALL ON EJEMPLAR TO ADMINISTRADOR;
GRANT ALL ON ESTANTE TO ADMINISTRADOR;
GRANT ALL ON LIBRO TO ADMINISTRADOR;
GRANT ALL ON PLANTA TO ADMINISTRADOR;
GRANT ALL ON DESABASTECIMIENTO TO ADMINISTRADOR;
GRANT ALL ON PEDIDO TO ADMINISTRADOR;
GRANT SELECT ON MENSAJE_PENDIENTE TO ADMINISTRADOR;
GRANT CREATE SESSION TO ADMINISTRADOR;
CREATE PUBLIC SYNONYM ADMINISTRADOR FOR ADMINISTRADOR;


CREATE OR REPLACE TRIGGER CASO_DESABASTECIMIENTO BEFORE INSERT OR DELETE OR UPDATE
  ON EJEMPLAR FOR EACH ROW
DECLARE
  ET NUMBER;
  EP NUMBER;
  ED NUMBER;
  ID_LIBRO NUMBER;
  FH VARCHAR(20);
  TITULO_LIBRO VARCHAR(100);
BEGIN
  IF INSERTING THEN
    SELECT LIBRO_ID INTO ID_LIBRO FROM EDICION WHERE ISBN = :NEW.EDICION_ISBN;
    SELECT EJEMPLARES_TOTALES INTO ET FROM LIBRO WHERE ID = ID_LIBRO;
    UPDATE LIBRO SET EJEMPLARES_TOTALES = (ET + 1) WHERE ID = ID_LIBRO;
    UPDATE LIBRO SET BAJA_DISPONIBILIDAD = 0 WHERE ID = ID_LIBRO;
  ELSIF DELETING THEN
    SELECT LIBRO_ID INTO ID_LIBRO FROM EDICION WHERE ISBN = :OLD.EDICION_ISBN;
    SELECT EJEMPLARES_TOTALES INTO ET FROM LIBRO WHERE ID = ID_LIBRO;
    UPDATE LIBRO SET EJEMPLARES_TOTALES = (ET - 1) WHERE ID = ID_LIBRO;
    IF ET = 1 THEN
      UPDATE LIBRO SET BAJA_DISPONIBILIDAD = 1 WHERE ID = ID_LIBRO;
      SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') INTO FH FROM DUAL;
      INSERT INTO DESABASTECIMIENTO(LIBRO_ID, FECHA_HORA) VALUES (ID_LIBRO, FH);
    END IF;
    IF :OLD.PRESTADO = 1 THEN
      SELECT EJEMPLARES_PRESTADOS INTO EP FROM LIBRO WHERE ID = ID_LIBRO;
      UPDATE LIBRO SET EJEMPLARES_PRESTADOS = (EP - 1) WHERE ID = ID_LIBRO;
    ELSE
      SELECT EJEMPLARES_PRESTADOS INTO EP FROM LIBRO WHERE ID = ID_LIBRO;
      IF ET <> 1 AND ET - 1 = EP THEN
        UPDATE LIBRO SET BAJA_DISPONIBILIDAD = 1 WHERE ID = ID_LIBRO;
        SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') INTO FH FROM DUAL;
	INSERT INTO DESABASTECIMIENTO(LIBRO_ID, FECHA_HORA) VALUES (ID_LIBRO, FH);
      END IF;
    END IF;
    IF :OLD.MAL_ESTADO = 1 THEN
      SELECT EJEMPLARES_DANIADOS INTO ED FROM LIBRO WHERE ID = ID_LIBRO;
      UPDATE LIBRO SET EJEMPLARES_DANIADOS = (ED - 1) WHERE ID = ID_LIBRO;
    END IF;
  ELSIF UPDATING THEN
    IF :OLD.PRESTADO <> :NEW.PRESTADO THEN
      SELECT LIBRO_ID INTO ID_LIBRO FROM EDICION WHERE ISBN = :NEW.EDICION_ISBN;
      SELECT EJEMPLARES_PRESTADOS INTO EP FROM LIBRO WHERE ID = ID_LIBRO;
      IF :NEW.PRESTADO = 1 THEN
        UPDATE LIBRO SET EJEMPLARES_PRESTADOS = (EP + 1) WHERE ID = ID_LIBRO;
        SELECT EJEMPLARES_TOTALES INTO ET FROM LIBRO WHERE ID = ID_LIBRO;
        IF ET = (EP + 1) THEN
          UPDATE LIBRO SET BAJA_DISPONIBILIDAD = 1 WHERE ID = ID_LIBRO;
          SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') INTO FH FROM DUAL;
	  INSERT INTO DESABASTECIMIENTO(LIBRO_ID, FECHA_HORA) VALUES (ID_LIBRO, FH);
        END IF;
      ELSE
        UPDATE LIBRO SET EJEMPLARES_PRESTADOS = (EP - 1) WHERE ID = ID_LIBRO;
        UPDATE LIBRO SET BAJA_DISPONIBILIDAD = 0 WHERE ID = ID_LIBRO;
      END IF;
    END IF;
    IF :OLD.MAL_ESTADO <> :NEW.MAL_ESTADO THEN
      SELECT LIBRO_ID INTO ID_LIBRO FROM EDICION WHERE ISBN = :NEW.EDICION_ISBN;
      SELECT EJEMPLARES_DANIADOS INTO ED FROM LIBRO WHERE ID = ID_LIBRO;
      IF :NEW.MAL_ESTADO = 1 THEN
        UPDATE LIBRO SET EJEMPLARES_DANIADOS = (ED + 1) WHERE ID = ID_LIBRO;
        SELECT EJEMPLARES_TOTALES INTO ET FROM LIBRO WHERE ID = ID_LIBRO;
        IF ET = (ED + 1) THEN
          SELECT TITULO INTO TITULO_LIBRO FROM LIBRO WHERE ID = ID_LIBRO;
          SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') INTO FH FROM DUAL;
	  INSERT INTO PEDIDO(LIBRO_NOMBRE, FECHA_HORA) VALUES (TITULO_LIBRO, FH);
        END IF;
      ELSE
        UPDATE LIBRO SET EJEMPLARES_DANIADOS = (ED - 1) WHERE ID = ID_LIBRO;
      END IF;
    END IF;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER BORRAR_PRESTAMO BEFORE DELETE
  ON ALQUILER FOR EACH ROW
BEGIN
  UPDATE EJEMPLAR SET PRESTADO = 0 WHERE EDICION_ISBN = :OLD.EDICION_ISBN AND ID = :OLD.EJEMPLAR_ID;
END;
/

CREATE OR REPLACE TRIGGER VALIDAR_PRESTAMO BEFORE INSERT
  ON ALQUILER FOR EACH ROW
DECLARE
  V NUMBER;
BEGIN
  SELECT VETADO INTO V FROM USUARIO WHERE CEDULA = :NEW.USUARIO_CEDULA;
  IF V = 1 THEN
    RAISE_APPLICATION_ERROR(-20001,'EL USUARIO ESTA VETADO.');
  END IF;
END;
/

CREATE OR REPLACE TRIGGER VALIDAR_DEVOLUCION BEFORE UPDATE OR DELETE
  ON ALQUILER FOR EACH ROW
DECLARE
  FH VARCHAR(20);
  UFHD VARCHAR2(20);
BEGIN
  SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') INTO FH FROM DUAL;
  SELECT ULTIMA_FECHA_HORA_DEVOLUCION INTO UFHD FROM USUARIO WHERE CEDULA = :OLD.USUARIO_CEDULA;
  IF MONTHS_BETWEEN (TO_DATE (FH, 'YYYY-MM-DD HH24:MI:SS'), TO_DATE (UFHD, 'YYYY-MM-DD HH24:MI:SS')) > 1 THEN
    UPDATE USUARIO SET VETADO = 1 WHERE CEDULA = :OLD.USUARIO_CEDULA;
  END IF;
END;
/


CREATE OR REPLACE TRIGGER CASO_NUEVOUSUARIO AFTER INSERT
  ON NOMBRE FOR EACH ROW
DECLARE
BEGIN
  INSERT INTO MENSAJE_PENDIENTE(NOMBRE_USUARIO, MENSAJE) VALUES (:NEW.NOMBRE_USER, 'BIENVENIDO ' || :NEW.NOMBRE_USER || ' ! ');
END;
/

CREATE OR REPLACE PROCEDURE NORMALIZAR (ENTRADA IN VARCHAR2, SALIDA OUT VARCHAR2)
AS
BEGIN
  SALIDA := UPPER(ENTRADA);
END NORMALIZAR;
/

create or replace procedure validarCed(ced IN varchar2, bandera OUT number)
as
    aux number;
    par number;
    impar number;
    verificador number;
    suma number;
    decena varchar(3);
begin
    suma:=0;
    aux:=0;
    verificador:=0;
    if( aux<10 and aux>10)then
        bandera:=1;
    else
        if(to_number(substr(ced,1,2))>24 and to_number(substr(ced,1,2))<0)then
            bandera:=1;
        elsif(to_number(substr(ced,3,1))>0 and to_number(substr(ced,3,1))<6)then
            bandera:=1;
        else
            for i in 1 .. 9 loop
                 aux:=to_number(substr(ced,i,1));
                if(mod(i,2)=0) then
                    suma:=suma+(aux*1);
                else
                    aux:=aux*2;
                    if(aux>=10)then
                        aux:=aux-9;
                    end if;
                    suma:=suma+aux;
                end if;
            end loop;
            verificador:=to_number(substr(ced,10,1));
            if(mod(suma,10)=0) then
                suma:=0;
            else
                decena:=to_char(suma);
                suma:=to_number(substr(decena,2,1));
                suma:=10-suma;
            end if;
            if(suma=verificador)then
                bandera:=0;
            else
                bandera:=1;
            end if;
        end if;
    end if;
end validarCed;

/

CREATE OR REPLACE PROCEDURE TIPORESPUESTA(RESPUESTA IN VARCHAR2, VERIFICADOR OUT NUMBER)
AS
    AUX NUMBER;
BEGIN
    AUX:=0;
    AUX:=TO_NUMBER(RESPUESTA);
    VERIFICADOR:=0;
    EXCEPTION
        WHEN VALUE_ERROR THEN
        VERIFICADOR:=1;
END TIPORESPUESTA;
/

CREATE PUBLIC SYNONYM NORMALIZAR FOR ADMIN3.NORMALIZAR;
GRANT EXECUTE ON NORMALIZAR TO BIBLIOTECARIO;
GRANT EXECUTE ON NORMALIZAR TO ADMINISTRADOR;
CREATE PUBLIC SYNONYM VALIDARCED FOR ADMIN3.VALIDARCED;
GRANT EXECUTE ON VALIDARCED TO BIBLIOTECARIO;
GRANT EXECUTE ON VALIDARCED TO ADMINISTRADOR;
CREATE PUBLIC SYNONYM TIPORESPUESTA FOR ADMIN3.TIPORESPUESTA;
GRANT EXECUTE ON TIPORESPUESTA TO BIBLIOTECARIO;
GRANT EXECUTE ON TIPORESPUESTA TO ADMINISTRADOR;
