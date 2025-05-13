package com.pgv.bookshelfreader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgv.bookshelfreader.model.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {
}