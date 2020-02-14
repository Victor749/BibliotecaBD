CREATE OR REPLACE TRIGGER caso_NuevoUsuario AFTER INSERT
  ON all_users FOR EACH ROW 
BEGIN
  INSERT into mensaje_pendiente (nombre_usuario,mensaje) values (:new.username, "Bienvenido " || :new.usermane || "!"); 
END;
/
