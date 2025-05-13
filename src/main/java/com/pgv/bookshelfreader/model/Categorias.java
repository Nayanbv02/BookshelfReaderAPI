package com.pgv.bookshelfreader.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categorias {

    @Id
    private Integer id_categoria;

    private String nombre_categoria;

    public Categorias() {}

    public Categorias(Integer id_categoria, String nombre_categoria) {
        this.id_categoria = id_categoria;
        this.nombre_categoria = nombre_categoria;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
}
