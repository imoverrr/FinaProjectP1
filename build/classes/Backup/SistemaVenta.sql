-- //CREACION DE LA BASE DE DATOS
CREATE DATABASE IF NOT EXISTS sistemaventa;
use sistemaventa;


-- // CREACION DE LA TABLA USUARIOS
CREATE TABLE usuarios (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    pass VARCHAR(100) NOT NULL
); 


-- // CREACION DE LA TABLA CLIENTE
CREATE TABLE clientes (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	dni VARCHAR(100) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	telefono VARCHAR(100) NOT NULL,
	direccion VARCHAR(100) NOT NULL
);

-- // CREACION DE LA TABLA PROVEEDOR
CREATE TABLE proveedor (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	dni VARCHAR(100) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	telefono VARCHAR(100) NOT NULL,
	direccion VARCHAR(100) NOT NULL
);


-- //CREACION DE LA TABLA PRODUCTO
CREATE TABLE producto (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codigo VARCHAR(100) NOT NULL,
	descripcion VARCHAR(100) NOT NULL,
	cantidad INT(11) NOT NULL,
	precio DOUBLE NOT NULL
);

-- //CREACION DE LA TABLA VENTA
CREATE TABLE venta (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idCliente INT NOT NULL,
	idUsuario INT NOT NULL, -- // En este caso seria el vendedor
	idProducto INT NOT NULL,
	total DOUBLE NOT NULL,
	FOREIGN KEY (idCliente) REFERENCES clientes(id),
	FOREIGN KEY (idUsuario) REFERENCES usuarios(id),
	FOREIGN KEY (idProducto) REFERENCES producto(id)
);



-- // INSERSION DE DATOS EN LA TABLA USUARIOS
INSERT INTO usuarios (nombre, correo, pass) VALUES 
('luiggi', 'luiggi@gmail.com','1234'),
('admin', 'admin@gmail.com','123'),
('Juan Perez', 'juan.perez@email.com', 'clave123'),
('Maria Rodriguez', 'maria.rodriguez@email.com', 'password456'),
('Carlos Gomez', 'carlos.gomez@email.com', 'securePass789'),
('Laura Martinez', 'laura.martinez@email.com', '1234abc'),
('Roberto Sanchez', 'roberto.sanchez@email.com', 'passWord321'),
('Ana Torres', 'ana.torres@email.com', 'userPass567'),
('Daniel Ramirez', 'daniel.ramirez@email.com', 'secret123'),
('Sofia Herrera', 'sofia.herrera@email.com', 'password789'),
('Pedro Lopez', 'pedro.lopez@email.com', 'securePass987'),
('Carmen Garcia', 'carmen.garcia@email.com', 'pass123word');

-- // INSERCIONES EN LA TABLA CLIENTES
INSERT INTO clientes (dni, nombre, telefono, direccion) VALUES
    ('123456789A', 'Juan Pérez', '555-1234', 'Calle A, Ciudad X'),
    ('987654321B', 'María Rodríguez', '555-5678', 'Calle B, Ciudad Y'),
    ('567890123C', 'Carlos González', '555-8765', 'Calle C, Ciudad Z'),
    ('234567890D', 'Laura Martínez', '555-4321', 'Calle D, Ciudad W'),
    ('345678901E', 'Pedro Sánchez', '555-9876', 'Calle E, Ciudad V');

-- // INSERCIONES EN LA TABLA PROVEEDOR
INSERT INTO proveedor (dni, nombre, telefono, direccion) VALUES
    ('987654321X', 'Proveedor A', '555-1111', 'Calle Proveedor A, Ciudad P'),
    ('123456789Y', 'Proveedor B', '555-2222', 'Calle Proveedor B, Ciudad Q'),
    ('234567890Z', 'Proveedor C', '555-3333', 'Calle Proveedor C, Ciudad R'),
    ('345678901W', 'Proveedor D', '555-4444', 'Calle Proveedor D, Ciudad S'),
    ('456789012V', 'Proveedor E', '555-5555', 'Calle Proveedor E, Ciudad T');

-- // INSERCIONES EN LA TABLA PRODUCTO
INSERT INTO producto (codigo, descripcion, cantidad, precio) VALUES
('P001', 'Laptop', 50, 1200.00),
('P002', 'Teléfono', 100, 500.00),
('P003', 'Monitor', 30, 300.00),
('P004', 'Teclado', 80, 50.00),
('P005', 'Mouse', 120, 20.00),
('P006', 'Impresora', 15, 150.00),
('P007', 'Altavoces', 40, 80.00),
('P008', 'Cámara', 25, 200.00),
('P009', 'Disco Duro', 60, 100.00),
('P010', 'Tablet', 35, 250.00);

-- INSERCIONES EN LA TABLA VENTA
INSERT INTO venta (idCliente, idUsuario, idProducto, total) VALUES
(1, 4, 3, 150.00),
(2, 5, 5, 500.00),
(3, 6, 2, 800.00),
(4, 7, 4, 80.00),
(5, 8, 6, 300.00),
(1, 9, 8, 400.00),
(2, 10, 1, 1200.00),
(3, 11, 7, 160.00),
(4, 12, 9, 900.00),
(5, 4, 10, 250.00),
(1, 5, 3, 150.00),
(2, 6, 5, 500.00);

-- // CONSULTA
-- SELECT * FROM usuarios;
