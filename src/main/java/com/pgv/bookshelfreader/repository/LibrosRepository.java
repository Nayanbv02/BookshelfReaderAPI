package com.pgv.bookshelfreader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgv.bookshelfreader.model.Libros;

public interface LibrosRepository extends JpaRepository<Libros, Long> {

}
