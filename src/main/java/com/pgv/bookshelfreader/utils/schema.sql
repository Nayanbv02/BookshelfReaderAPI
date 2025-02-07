CREATE DATABASE bookshelf_reader;

USE bookshelf_reader;

CREATE TABLE libros (
    id_libro BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_pdf VARCHAR(255) NOT NULL,
    ruta_pdf VARCHAR(255) NOT NULL,
    favorito VARCHAR(5),
    ultima_pagina BIGINT,
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    clave VARCHAR(255) NOT NULL
);

CREATE TABLE marcadores (
    id_marcador BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_pagina BIGINT NOT NULL,
    color VARCHAR(50) NOT NULL,
    fecha DATETIME NOT NULL,
    id_libro BIGINT NOT NULL,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro) ON DELETE CASCADE ON UPDATE CASCADE
);