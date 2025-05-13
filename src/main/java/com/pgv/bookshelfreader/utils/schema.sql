CREATE DATABASE bookshelf_reader;

USE bookshelf_reader;

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    clave VARCHAR(255) NOT NULL
);

CREATE TABLE categorias (
    id_categoria INT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL
);

CREATE TABLE libros (
    id_libro BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_pdf VARCHAR(255) NOT NULL,
    ruta_pdf VARCHAR(255) NOT NULL,
    favorito VARCHAR(5),
    ultima_pagina BIGINT,
    usuario_id BIGINT,
    categoria INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (categoria) REFERENCES categorias(id_categoria) ON DELETE CASCADE
);

CREATE TABLE marcadores (
    id_marcador BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_pagina BIGINT NOT NULL,
    color VARCHAR(50) NOT NULL,
    fecha DATETIME NOT NULL,
    id_libro BIGINT NOT NULL,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE historialLectura (
    id_historial INT PRIMARY KEY,
    fecha_lectura DATETIME NOT NULL,
    duracion INT NOT NULL,
    id_libro BIGINT,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro)
);
