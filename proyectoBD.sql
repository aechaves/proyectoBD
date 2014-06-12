-- Integrantes Angelo Chávez - Eduardo Benmergui - Facundo Molina
-- Trabajo Práctico Integrador, Base de Datos 2014.
 
DROP DATABASE IF EXISTS proyectoBD;
CREATE DATABASE proyectoBD;
USE proyectoBD;


-- Tabla Ciudad
DROP TABLE IF EXISTS Ciudad;
CREATE TABLE Ciudad(
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL
);
-- Inserción de datos en la tabla Ciudad
INSERT INTO Ciudad (nombre) VALUES 
('Río Cuarto'),
('Córdoba'),
('Villa María'),
('San Francisco');


-- Tabla Usuario
DROP TABLE IF EXISTS Usuario; 	
CREATE TABLE Usuario(
	dni INT(11) NOT NULL PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	calleYnum VARCHAR(50),
	movil INT(20),
	telFijo INT(20),
	contraseña VARCHAR(15) NOT NULL,
	id_ciudad INT(11) NOT NULL,
	CONSTRAINT fk_usuario_ciudad FOREIGN KEY (id_ciudad) REFERENCES Ciudad (id) 
	ON DELETE RESTRICT
);
-- Inserción de datos en la tabla Usuario
INSERT INTO Usuario(dni,nombre,apellido,calleYnum,movil,telFijo,contraseña,id_ciudad) VALUES
('31299365','Juan','Rodriguez','San Martín 43','3584256357','4889633','1234','1'),
('31299366','Carlos','Lopez','Tucuman 117','3584256863','4889631','1234','2'),
('31299367','Laura','Perez','Buenos Aires 431','3584256253','4889125','1234','1'),
('31299368','German','Gonzales','Lamadrid 343','3584256589','4889102','1234','3'),
('31299369','María','Altamirano','San Juan 981','3584256120','4889658','1234','4'),
('31299370','Ricardo','Bazan','Mitre 1024','3584256542','4889710','1234','4'),
('31299371','Adriana','Galindez','La Rioja 589','3584256321','4889671','1234','1'),
('31299372','Omar','Miranda','Buenos Aires 826','3584256145','4889689','1234','3'),
('31299373','Juan','Riveros','San Martín 110','3584256991','4889612','1234','2');



-- Tabla Marca
DROP TABLE IF EXISTS Marca;
CREATE TABLE Marca(
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL
);
-- Inserción de datos en la tabla Marca
INSERT INTO Marca(nombre) VALUES
('Ford'),
('Renault'),
('Honda'),
('Yamaha'),
('Toyota'),
('Chevrolet'),
('Volvo');



-- Tabla Modelo
DROP TABLE IF EXISTS Modelo;
CREATE TABLE Modelo(
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
	id_marca INT(11) NOT NULL,
	CONSTRAINT fk_modelo_marca FOREIGN KEY (id_marca) REFERENCES Marca(id) 
	ON DELETE CASCADE
);
-- Inserción de datos en la tabla Modelo
INSERT INTO Modelo(nombre,id_marca) VALUES
('Focus','1'),
('Fiesta','1'),
('Megane','2'),
('Clio','2'),
('Biz','3'),
('Crypton','4'),
('Hilux','5'),
('Corsa','6'),
('Astra','6'),
('260','7');



-- Tabla Vehiculo
DROP TABLE IF EXISTS Vehiculo;
CREATE TABLE Vehiculo(
	patente VARCHAR(6) NOT NULL PRIMARY KEY,
	estado ENUM("Excelente","Bueno","Regular","Chocado") NOT NULL, 
	km INT(11),
	año VARCHAR(4) NOT NULL,
	dni_usuario INT(11) NOT NULL,
	id_modelo INT(11) NOT NULL,
	CONSTRAINT fk_vehiculo_usuario FOREIGN KEY (dni_usuario) REFERENCES Usuario (dni)
	ON DELETE CASCADE,
	CONSTRAINT fk_vehiculo_modelo FOREIGN KEY (id_modelo) REFERENCES Modelo (id)
	ON DELETE RESTRICT	
);
-- Inserción de datos en la tabla Vehiculo
INSERT INTO Vehiculo(patente,estado,km,año,dni_usuario,id_modelo) VALUES 
('AAA999','Bueno','3589','2007','31299366','3'),
('AAA888','Excelente','3500','2012','31299365','1'),
('AAA777','Regular','80000','1994','31299371','8'),
('AAA555','Bueno','10000','2000','31299367','5'),
('AAA444','Regular','70000','2008','31299368','6'),
('AAA333','Chocado','65000','2003','31299369','7'),
('AAA222','Excelente','7000','2011','31299370','7'),
('AAA111','Bueno','32000','2005','31299372','10');



-- Trigger para registrar vehiculos eliminados en la tabla VehiculosEliminados
DELIMITER $$
	CREATE TRIGGER triggerEliminarVehiculo
	BEFORE DELETE ON Vehiculo FOR EACH ROW
		BEGIN
			INSERT INTO VehiculosEliminados(fecha,dni_usuario,patente_vehiculo) VALUES
			(NOW(),OLD.dni_usuario,OLD.patente);
		END $$
DELIMITER ;



-- Tabla Auto
DROP TABLE IF EXISTS Auto;
CREATE TABLE Auto(
	patente_vehiculo VARCHAR(6) NOT NULL PRIMARY KEY,
	cantAsientos INT(2) NOT NULL,
	CONSTRAINT fk_auto_vehiculo FOREIGN KEY (patente_vehiculo) REFERENCES Vehiculo (patente) 
	ON DELETE CASCADE
);
-- Inserción de datos en la tabla Auto
INSERT INTO Auto(patente_vehiculo,cantAsientos) VALUES
	('AAA888','4'),
('AAA999','4'),
('AAA777','2');



-- Tabla Moto
DROP TABLE IF EXISTS Moto;
CREATE TABLE Moto(
	patente_vehiculo VARCHAR(6) NOT NULL PRIMARY KEY,
	carenada BOOL NOT NULL,
	CONSTRAINT fk_moto_vehiculo FOREIGN KEY (patente_vehiculo) REFERENCES Vehiculo (patente)
	ON DELETE CASCADE
);
-- Inserción de datos en la tabla Moto
INSERT INTO Moto(patente_vehiculo,carenada) VALUES
('AAA555','1'),
('AAA444','0');



-- Tabla PickUp	
DROP TABLE IF EXISTS PickUp;
CREATE TABLE PickUp(
	patente_vehiculo VARCHAR(6) NOT NULL PRIMARY KEY,
	traccion ENUM ("Simple","Doble") NOT NULL,
	CONSTRAINT fk_pickup_vehiculo FOREIGN KEY (patente_vehiculo) REFERENCES Vehiculo (patente)
	ON DELETE CASCADE
);
-- Inserción de datos en la tabla PickUp
INSERT INTO PickUp(patente_vehiculo,traccion) VALUES
('AAA333','Simple'),
('AAA222','Doble');



-- Tabla Camión
DROP TABLE IF EXISTS Camion;
CREATE TABLE Camion(
	patente_vehiculo VARCHAR(6) NOT NULL PRIMARY KEY,
	tara DOUBLE NOT NULL,
	CONSTRAINT fk_camion_vehiculo FOREIGN KEY (patente_vehiculo) REFERENCES Vehiculo (patente)
	ON DELETE CASCADE
);
-- Inserción de datos en la tabla Camion
INSERT INTO Camion(patente_vehiculo,tara) VALUES
('AAA111','3500');



-- Tabla Anuncio
DROP TABLE IF EXISTS Anuncio;
CREATE TABLE Anuncio(
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	titulo VARCHAR(120) NOT NULL,
	descripcion TEXT NOT NULL,
	fechaAlta DATE NOT NULL,
	fechaBaja DATE,
	tipo ENUM("Venta","Permuta") NOT NULL,
	monto INT NOT NULL,
	dni_usuario INT(11) NOT NULL,
	patente_vehiculo VARCHAR(6) NOT NULL,
	CONSTRAINT fk_anuncio_usuario FOREIGN KEY (dni_usuario) REFERENCES Usuario (dni)
	ON DELETE CASCADE,
	CONSTRAINT fk_anuncio_vehiculo FOREIGN KEY (patente_vehiculo) REFERENCES Vehiculo (patente)
	ON DELETE CASCADE
);
-- Inserción de datos en la tabla Anuncio
INSERT INTO Anuncio(titulo,descripcion,fechaAlta,tipo,monto,dni_usuario,patente_vehiculo) VALUES
('Vendo auto Ford Focus','Excelente estado. No permuta solo venta.',NOW(),'Venta','80000','31299365','AAA888'),
('Permuto moto Yamaha','Buen estado. Permuto por un auto.',NOW(),'Permuta','10000','31299368','AAA444'),
('Vendo camioneta','Buen funcionamiento aunque está chocada',NOW(),'Venta','60000','31299369','AAA333');



-- Trigger para controlar el solapamiento de fechas en la insercion de anuncios 
-- En cuanto a este trigger aclaramos que se considera que cuando se agrega quiere agregar un anuncio la fecha de alta viene dada por
-- la función NOW() y la fecha de baja queda NULL (es decir, se supone que el anuncio va a estar publicado hasta que se desee eliminarlo)
-- con lo cual de esta manera va a haber solapamiento de fecha cuando se quiera agegar un anuncio sobre un vehiculo del cual ya hay un 
-- post vigente, es decir su fecha de baja es null.

DELIMITER $$
	CREATE TRIGGER triggerControlSolapamientoFecha
	BEFORE INSERT ON Anuncio FOR EACH ROW
		BEGIN
			DECLARE nro INT;
			SELECT COUNT(*) INTO nro FROM Anuncio GROUP BY patente_vehiculo,fechaBaja,tipo 
			HAVING patente_vehiculo=NEW.patente_vehiculo AND tipo=NEW.tipo AND (fechaBaja IS NULL);
			IF (nro=1) THEN 
				SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Ya existe un post vigente correspondiente a este vehiculo. " ;
			END IF;
		END $$
DELIMITER ;



-- Tabla Consulta
DROP TABLE IF EXISTS Consulta;
CREATE TABLE Consulta(
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	descripcion VARCHAR(120) NOT NULL,
	dni_usuario INT(11) NOT NULL,
	id_anuncio INT(11) NOT NULL,
	CONSTRAINT fk_consulta_usuario FOREIGN KEY (dni_usuario) REFERENCES Usuario(dni) 
	ON DELETE CASCADE,
	CONSTRAINT fk_consulta_anuncio FOREIGN KEY (id_anuncio) REFERENCES Anuncio(id)
	ON DELETE CASCADE
);
-- Inserción de datos en la tabla Consulta;
INSERT INTO Consulta(descripcion,dni_usuario,id_anuncio) VALUES
('¿Qué combustible requiere?','31299373','1'),
('¿Qué motor tiene?','31299372','2');



-- Trigger control del usuario que hace la consulta sobre el post
DELIMITER $$
	CREATE TRIGGER triggerControlUsuarioConsulta
	BEFORE INSERT ON Consulta FOR EACH ROW
		BEGIN
			DECLARE nro INT;
			SELECT COUNT(*) INTO nro FROM Anuncio GROUP BY id,dni_usuario
			HAVING id=NEW.id_anuncio AND dni_usuario=NEW.dni_usuario;
			IF (nro=1) THEN 
				SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No se puede hacer una consulta sobre un anuncio del cual es dueño" ;
			END IF;
		END $$
DELIMITER ;



-- Tabla Respuesta
DROP TABLE IF EXISTS Respuesta;
CREATE TABLE Respuesta(
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	descripcion VARCHAR(120) NOT NULL,
	dni_usuario INT(11) NOT NULL,
	id_consulta INT(11) NOT NULL,
	CONSTRAINT fk_respuesta_usuario FOREIGN KEY (dni_usuario) REFERENCES Usuario(dni)
	ON DELETE CASCADE,
	CONSTRAINT fk_respuesta_consulta FOREIGN KEY (id_consulta) REFERENCES Consulta(id)
	ON DELETE CASCADE
);
-- Inserción de datos en la tabla Respuesta
INSERT INTO Respuesta(descripcion,dni_usuario,id_consulta) VALUES
('Nafta','31299365','1'),
('Gas','31299365','1'),
('Gas','31299365','1'),
('Gas','31299365','1'),
('Gas','31299365','1');  -- Respuesta repetida para la prueba del siguiente trigger



-- Trigger control que la cantidad de respuestas de una consulta no sea mayor a 5 y
-- que un usuario no haga una respuesta sobre un anuncio que no es el suyo.
DELIMITER $$
	CREATE TRIGGER triggerControlRespuestas
	BEFORE INSERT ON Respuesta FOR EACH ROW
		BEGIN
			DECLARE nro INT;
			SELECT COUNT(*) INTO nro FROM Respuesta GROUP BY id_consulta
			HAVING id_consulta=NEW.id_consulta;
			IF (nro=5) THEN 
				SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Ya se ha alcanzado el máximo de respuestas posibles." ;
			END IF;
			-- SELECT COUNT(*) INTO nro FROM Consulta GROUP BY id,id_anuncio
			-- HAVING id=NEW.id_consulta AND NEW.dni_usuario IN (SELECT dni_usuario FROM Anuncio
			-- WHERE id_anuncio=Anuncio.id);
			-- IF (nro=0) THEN 
			IF (new.dni_usuario not in(SELECT a.dni_usuario FROM Anuncio a JOIN Consulta c ON a.id=c.id_anuncio 
			WHERE c.id=NEW.id_consulta)) THEN 
				SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No se puede responder una consulta sobre un anuncio del cual no es dueño" ;
			END IF;
		END $$
DELIMITER ;



-- Tabla Eliminacion de Vehículos
DROP TABLE IF EXISTS VehiculosEliminados;
CREATE TABLE VehiculosEliminados(
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fecha DATE NOT NULL,
	dni_usuario INT(11) NOT NULL,
	patente_vehiculo VARCHAR(6) NOT NULL,
	CONSTRAINT fk_vehiculosEliminados_usuario FOREIGN KEY (dni_usuario) REFERENCES Usuario(dni)
	ON DELETE CASCADE
);
