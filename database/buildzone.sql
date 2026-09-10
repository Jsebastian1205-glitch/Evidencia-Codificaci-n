CREATE DATABASE IF NOT EXISTS buildzone
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE buildzone;
SHOW TABLES;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(100) UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS marca (
    id_marca INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT NULL
);

CREATE TABLE IF NOT EXISTS categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT NULL
);

CREATE TABLE IF NOT EXISTS producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    id_marca INT NOT NULL,
    id_categoria INT NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT NULL,
    imagen VARCHAR(255) NULL,
    CONSTRAINT fk_producto_marca
        FOREIGN KEY (id_marca) REFERENCES marca(id_marca),
    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE IF NOT EXISTS  especificacion (
    id_especificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    atributo VARCHAR(100) NOT NULL,
    valor VARCHAR(255) NOT NULL,
    unidad VARCHAR(50) NULL,
    CONSTRAINT fk_especificacion_producto
        FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS fuente_precio (
    id_fuente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    sitio_web VARCHAR(255) NULL,
    descripcion TEXT NULL
);

CREATE TABLE IF NOT EXISTS precio (
    id_precio INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    id_fuente INT NOT NULL,
    valor DECIMAL(12,2) NOT NULL,
    fecha_consulta DATETIME NOT NULL,
    url_producto VARCHAR(255) NULL,
    CONSTRAINT fk_precio_producto
        FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
        ON DELETE CASCADE,
    CONSTRAINT fk_precio_fuente
        FOREIGN KEY (id_fuente) REFERENCES fuente_precio(id_fuente)
);

CREATE TABLE IF NOT EXISTS comparacion (
    id_comparacion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    titulo VARCHAR(150) NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comparacion_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE IF NOT EXISTS detalle_comparacion (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_comparacion INT NOT NULL,
    id_producto INT NOT NULL,
    CONSTRAINT fk_detalle_comparacion
        FOREIGN KEY (id_comparacion) REFERENCES comparacion(id_comparacion)
        ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE IF NOT EXISTS noticia (
    id_noticia INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    contenido TEXT NOT NULL,
    fecha_publicacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_noticia_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

INSERT IGNORE INTO marca (nombre, descripcion) VALUES
('NVIDIA', 'Fabricante de tarjetas gráficas.'),
('AMD', 'Fabricante de procesadores y tarjetas gráficas.'),
('Intel', 'Fabricante de procesadores y componentes.');

INSERT IGNORE INTO categoria (nombre, descripcion) VALUES
('Tarjeta gráfica', 'Componentes destinados al procesamiento gráfico.'),
('Procesador', 'Unidad central de procesamiento.'),
('Memoria RAM', 'Memoria principal del computador.');

INSERT IGNORE INTO producto
(id_marca, id_categoria, nombre, descripcion, imagen)
VALUES
((SELECT id_marca FROM marca WHERE nombre = 'NVIDIA'),
 (SELECT id_categoria FROM categoria WHERE nombre = 'Tarjeta gráfica'),
 'NVIDIA GeForce RTX 4070',
 'Tarjeta gráfica para alto rendimiento.', 'rtx4070.jpg'),

((SELECT id_marca FROM marca WHERE nombre = 'AMD'),
 (SELECT id_categoria FROM categoria WHERE nombre = 'Procesador'),
 'AMD Ryzen 7 7800X3D',
 'Procesador orientado a gaming.', 'ryzen7800x3d.jpg'),

((SELECT id_marca FROM marca WHERE nombre = 'Intel'),
 (SELECT id_categoria FROM categoria WHERE nombre = 'Procesador'),
 'Intel Core i7-14700K',
 'Procesador de alto rendimiento.', 'i7-14700k.jpg');

