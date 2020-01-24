CREATE OR REPLACE TRIGGER caso_desabastecimiento BEFORE INSERT OR DELETE OR UPDATE
  ON ejemplar FOR EACH ROW
DECLARE
  et number;
  ep number;
  ed number;
  id_libro number;
  fh varchar(20);
  titulo_libro varchar(100);
BEGIN
  IF INSERTING THEN
    select libro_id into id_libro from edicion where isbn = :new.edicion_isbn;
    select ejemplares_totales into et from libro where id = id_libro;
    UPDATE libro set ejemplares_totales = (et + 1) where id = id_libro;
    UPDATE libro set baja_disponibilidad = 0 where id = id_libro;
  ELSIF DELETING THEN
    select libro_id into id_libro from edicion where isbn = :old.edicion_isbn;
    select ejemplares_totales into et from libro where id = id_libro;
    UPDATE libro set ejemplares_totales = (et - 1) where id = id_libro;
    if et = 1 then
      UPDATE libro set baja_disponibilidad = 1 where id = id_libro;
      SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') INTO fh FROM dual;
      INSERT INTO desabastecimiento(libro_id, fecha_hora) VALUES (id_libro, fh);
    end if;
    if :old.prestado = 1 then
      select ejemplares_prestados into ep from libro where id = id_libro;
      UPDATE libro set ejemplares_prestados = (ep - 1) where id = id_libro;
    else
      select ejemplares_prestados into ep from libro where id = id_libro;
      if et <> 1 and et - 1 = ep then
        UPDATE libro set baja_disponibilidad = 1 where id = id_libro;
        SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') INTO fh FROM dual;
	INSERT INTO desabastecimiento(libro_id, fecha_hora) VALUES (id_libro, fh);
      end if;
    end if;
    if :old.mal_estado = 1 then
      select ejemplares_daniados into ed from libro where id = id_libro;
      UPDATE libro set ejemplares_daniados = (ed - 1) where id = id_libro;
    end if;
  ELSIF UPDATING THEN
    if :old.prestado <> :new.prestado then 
      select libro_id into id_libro from edicion where isbn = :new.edicion_isbn;
      select ejemplares_prestados into ep from libro where id = id_libro;
      if :new.prestado = 1 then
        UPDATE libro set ejemplares_prestados = (ep + 1) where id = id_libro;
        select ejemplares_totales into et from libro where id = id_libro;
        if et = (ep + 1) then
          UPDATE libro set baja_disponibilidad = 1 where id = id_libro;
          SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') INTO fh FROM dual;
	  INSERT INTO desabastecimiento(libro_id, fecha_hora) VALUES (id_libro, fh);
        end if;
      else 
        UPDATE libro set ejemplares_prestados = (ep - 1) where id = id_libro;
        UPDATE libro set baja_disponibilidad = 0 where id = id_libro;
      end if;
    end if;  
    if :old.mal_estado <> :new.mal_estado then 
      select libro_id into id_libro from edicion where isbn = :new.edicion_isbn;
      select ejemplares_daniados into ed from libro where id = id_libro;
      if :new.mal_estado = 1 then
        UPDATE libro set ejemplares_daniados = (ed + 1) where id = id_libro;
        select ejemplares_totales into et from libro where id = id_libro;
        if et = (ed + 1) then
          select titulo into titulo_libro from libro where id = id_libro;
          SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') INTO fh FROM dual;
	  INSERT INTO pedido(libro_nombre, fecha_hora) VALUES (titulo_libro, fh);
        end if;
      else 
        UPDATE libro set ejemplares_daniados = (ed - 1) where id = id_libro;
      end if;
    end if;
  END IF;
END;
/
