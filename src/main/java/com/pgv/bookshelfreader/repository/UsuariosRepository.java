package com.pgv.bookshelfreader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgv.bookshelfreader.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByNombre(String nombre);
}

