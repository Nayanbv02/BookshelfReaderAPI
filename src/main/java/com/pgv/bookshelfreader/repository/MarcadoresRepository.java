package com.pgv.bookshelfreader.repository;

import com.pgv.bookshelfreader.model.Marcadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MarcadoresRepository extends JpaRepository<Marcadores, Long> {

}

//List<Marcadores> findById(String id_libro);
//import java.util.List;