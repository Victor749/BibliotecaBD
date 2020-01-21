create table usuario
(
  cedula varchar2(10) not null,
  nombre varchar2(50) not null,
  direccion varchar2(100), 
  puede_prestamo number(1) check (puede_prestamo in (0,1)) not null,
  constraint usuario_pk primary key (cedula)
);
insert all
  into usuario (cedula, nombre, direccion, puede_prestamo) values ('0123456789', 'Azar Javed', 'Guarida Salamandra', 1)
  into usuario (cedula, nombre, direccion, puede_prestamo) values ('1234567890', 'Fringilla de Vigo', 'Capital del Imperio Nilfgaardiano', 1)
  into usuario (cedula, nombre, direccion, puede_prestamo) values ('0987654321', 'Borch Tres Grajos', 'Montañas de Zerrikania', 1)
  into usuario (cedula, nombre, direccion, puede_prestamo) values ('9876543210', 'Triss Merigold', 'Torre de Maribor', 1)
select * from dual;
create table autor
(
  id number not null,
  nombre varchar2(50) not null,
  constraint autor_pk primary key (id)
);
insert into autor (id, nombre) values (1, 'Anónimo');
insert into autor (id, nombre) values (2, 'Jaskier');
insert into autor (id, nombre) values (3, 'Vesemir');
insert into autor (id, nombre) values (4, 'Nenneke');
insert into autor (id, nombre) values (5, 'Regis');
create table editorial
(
  id number not null,
  nombre varchar2(100) not null,
  constraint editorial_pk primary key (id)
);
insert into editorial (id, nombre) values (1, 'Desconocida');
insert into editorial (id, nombre) values (2, 'Universidad de Oxenfurt');
insert into editorial (id, nombre) values (3, 'Kaer Morhen');
insert into editorial (id, nombre) values (4, 'Santuario de Melitele');
insert into editorial (id, nombre) values (5, 'Editorial del Ducado de Touissant');
create table libro
(
  id number not null,
  titulo varchar2(100) not null,
  autor_id number not null,
  constraint libro_pk primary key (id),
  constraint fk_autor foreign key (autor_id) references autor(id)
);
insert into libro (id, titulo, autor_id) values (1, 'Vampiros: Hechos y Mitos', 5);
insert into libro (id, titulo, autor_id) values (2, 'Espectros, Apariciones y Condenados', 3);
insert into libro (id, titulo, autor_id) values (3, 'Plantas del Campo', 4);
insert into libro (id, titulo, autor_id) values (4, 'Monstruos del Pantano', 3);
insert into libro (id, titulo, autor_id) values (5, 'Sátira a la Profecía de Ithlinne', 1);
insert into libro (id, titulo, autor_id) values (6, 'Balada del Lobo Blanco', 2);
insert into libro (id, titulo, autor_id) values (7, 'Medicina Forense', 4);
create table edicion
(
  isbn varchar2(13) not null,
  libro_id number not null,
  editorial_id number not null,
  numero number(2) not null,
  fecha varchar2(10),
  descripcion varchar2(200),
  constraint edicion_pk primary key (isbn),
  constraint fk_libro foreign key (libro_id) references libro(id),
  constraint fk_editorial foreign key (editorial_id) references editorial(id)
);
insert all
  into edicion (isbn, libro_id, editorial_id, numero, fecha, descripcion) values ('1111111111111', 1, 1, 1, '1112-04-01', 'Pergaminos, Lengua Antigua')
  into edicion (isbn, libro_id, editorial_id, numero, fecha, descripcion) values ('2222222222222', 1, 5, 2, '1216-07-02', 'Encuadernado, Traducido a Lengua Común, Incluye 8 nuevos mitos')
  into edicion (isbn, libro_id, editorial_id, numero, fecha, descripcion) values ('3333333333333', 5, 1, 1, '1019-11-22', 'Pergaminos, Lengua Élfica')
  into edicion (isbn, libro_id, editorial_id, numero, fecha, descripcion) values ('4444444444444', 2, 3, 1, '1212-04-06', 'Encuadernado, Lengua Común')
  into edicion (isbn, libro_id, editorial_id, numero, fecha, descripcion) values ('5555555555555', 3, 4, 1, '1213-05-07', 'Encuadernado, Lengua Común')
  into edicion (isbn, libro_id, editorial_id, numero, fecha, descripcion) values ('6666666666666', 4, 3, 1, '1219-09-12', 'Encuadernado, Lengua Común')
  into edicion (isbn, libro_id, editorial_id, numero, fecha, descripcion) values ('7777777777777', 6, 2, 1, '1222-12-11', 'Encuadernado, Lengua Común')
  into edicion (isbn, libro_id, editorial_id, numero, fecha, descripcion) values ('8888888888888', 7, 4, 1, '1211-12-08', 'Encuadernado, Lengua Antigua')
  into edicion (isbn, libro_id, editorial_id, numero, fecha, descripcion) values ('9999999999999', 7, 4, 2, '1223-11-09', 'Encuadernado, Traducido a Lengua Común')
select * from dual;
create table planta
(
  id number not null,
  nombre varchar2(50) not null,
  constraint planta_pk primary key (id)
);
insert into planta (id, nombre) values (1, 'Planta Baja');
insert into planta (id, nombre) values (2, '1er Piso');
insert into planta (id, nombre) values (3, '2do Piso');
create table estante
(
  planta_id number not null,
  id number not null,
  nombre varchar2(50) not null,
  constraint estante_pk primary key (planta_id, id),
  constraint fk_planta foreign key (planta_id) references planta(id)
);
insert into estante (planta_id, id, nombre) values (1, 1, 'Estante A');
insert into estante (planta_id, id, nombre) values (1, 2, 'Estante B');
insert into estante (planta_id, id, nombre) values (2, 1, 'Estante C');
insert into estante (planta_id, id, nombre) values (3, 1, 'Estante D');
create table ejemplar
(
  edicion_isbn varchar2(13) not null,
  id number not null,
  planta_id number not null,
  estante_id number not null,
  prestado number(1) check (prestado in (0,1)) not null,
  observaciones varchar2(200),
  constraint ejemplar_pk primary key (edicion_isbn, id),
  constraint fk_edicion foreign key (edicion_isbn) references edicion(isbn),
  constraint fk_planta_estante foreign key (planta_id, estante_id) references estante(planta_id, id)
);
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('1111111111111', 1, 0, 1, 1, ' ');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('1111111111111', 2, 0, 1, 1, 'Arrugado');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('2222222222222', 1, 0, 1, 1, ' ');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('2222222222222', 2, 0, 1, 1, 'Falta Página 10');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('3333333333333', 1, 0, 1, 2, ' ');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('3333333333333', 2, 0, 1, 2, 'Rasgado');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('4444444444444', 1, 0, 1, 2, ' ');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('4444444444444', 2, 0, 2, 1, 'Portada Mutilada');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('5555555555555', 1, 0, 2, 1, 'Faltan Páginas 12 y 45');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('5555555555555', 2, 0, 2, 1, ' ');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('6666666666666', 1, 0, 2, 1, 'Rayada la Kikimora en la página 34');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('7777777777777', 1, 0, 3, 1, ' ');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('8888888888888', 1, 0, 3, 1, 'Portada Mutilada');
insert into ejemplar (edicion_isbn, id, prestado, planta_id, estante_id, observaciones) values ('9999999999999', 1, 0, 3, 1, ' ');
create table alquiler
(
  usuario_cedula varchar2(100) not null,
  edicion_isbn varchar2(13) not null,
  ejemplar_id number not null,
  fecha_hora_prestamo varchar2(20) not null,
  fecha_hora_estimada_entrega varchar2(20) not null,
  fecha_hora_entrega varchar2(20),
  constraint alquiler_pk primary key (usuario_cedula , edicion_isbn, ejemplar_id, fecha_hora_prestamo),
  constraint fk_usuario foreign key (usuario_cedula) references usuario(cedula) on delete cascade,
  constraint fk_ejemplar foreign key (edicion_isbn, ejemplar_id) references ejemplar(edicion_isbn, id) on delete cascade
);
insert into alquiler(usuario_cedula, edicion_isbn, ejemplar_id, fecha_hora_prestamo, fecha_hora_estimada_entrega) values ('0123456789', '1111111111111', 1, '2019-12-27 09:10:02', '2019-12-28 00:00:00');
update ejemplar set prestado = 1 where edicion_isbn = '1111111111111' and id = 1;
insert into alquiler(usuario_cedula, edicion_isbn, ejemplar_id, fecha_hora_prestamo, fecha_hora_estimada_entrega) values ('0123456789', '2222222222222', 2, '2019-12-27 09:10:02', '2019-12-28 00:00:00');
update ejemplar set prestado = 1 where edicion_isbn = '2222222222222' and id = 2;
update usuario set puede_prestamo = 0 where cedula = '0123456789';
insert into alquiler(usuario_cedula, edicion_isbn, ejemplar_id, fecha_hora_prestamo, fecha_hora_estimada_entrega) values ('0987654321', '7777777777777', 1, '2019-12-27 11:00:23', '2019-12-28 12:30:00');
update ejemplar set prestado = 1 where edicion_isbn = '7777777777777' and id = 1;
update usuario set puede_prestamo = 0 where cedula = '0987654321';
update alquiler set fecha_hora_entrega = '2019-12-28 11:30:00' where usuario_cedula = '0987654321' and fecha_hora_prestamo = '2019-12-27 11:00:23';
update usuario set puede_prestamo = 1 where cedula = '0987654321';
update ejemplar set prestado = 0 where edicion_isbn = '7777777777777' and id = 1;

CREATE PUBLIC SYNONYM usuario FOR admin3.usuario;
CREATE PUBLIC SYNONYM alquiler FOR admin3.alquiler;
CREATE PUBLIC SYNONYM autor FOR admin3.autor;
CREATE PUBLIC SYNONYM edicion FOR admin3.edicion;
CREATE PUBLIC SYNONYM editorial FOR admin3.editorial;
CREATE PUBLIC SYNONYM ejemplar FOR admin3.ejemplar;
CREATE PUBLIC SYNONYM estante FOR admin3.estante;
CREATE PUBLIC SYNONYM libro FOR admin3.libro;
CREATE PUBLIC SYNONYM planta FOR admin3.planta;

alter session set "_ORACLE_SCRIPT"=true;

CREATE ROLE bibliotecario;
GRANT ALL ON usuario TO bibliotecario;
GRANT ALL ON alquiler TO bibliotecario;
GRANT SELECT ON autor TO bibliotecario;
GRANT SELECT ON edicion TO bibliotecario;
GRANT SELECT ON editorial TO bibliotecario;
GRANT ALL ON ejemplar TO bibliotecario;
GRANT SELECT ON estante TO bibliotecario;
GRANT SELECT ON libro TO bibliotecario;
GRANT SELECT ON planta TO bibliotecario;
GRANT create SESSION to bibliotecario;

CREATE PUBLIC SYNONYM roleBibliotecario FOR bibliotecario;


CREATE ROLE administrador;
GRANT ALL ON usuario TO administrador;
GRANT ALL ON alquiler TO administrador;
GRANT ALL ON autor TO administrador;
GRANT ALL ON edicion TO administrador;
GRANT ALL ON editorial TO administrador;
GRANT ALL ON ejemplar TO administrador;
GRANT ALL ON estante TO administrador;
GRANT ALL ON libro TO administrador;
GRANT ALL ON planta TO administrador;
GRANT create SESSION to administrador;

CREATE PUBLIC SYNONYM roleAdministrador FOR administrador;

commit;