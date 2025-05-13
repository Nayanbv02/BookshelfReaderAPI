package com.pgv.bookshelfreader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgv.bookshelfreader.model.HistorialLectura;

public interface HistorialLecturaRepository extends JpaRepository<HistorialLectura, Integer> {
}