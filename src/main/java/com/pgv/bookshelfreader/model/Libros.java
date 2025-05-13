package com.pgv.bookshelfreader.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_libro;

    private String nombre_pdf;
    private String ruta_pdf;
    private String favorito;
    private Long ultima_pagina;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "categoria", nullable = true)
    private Categorias categoria;

    public Libros() {}

    public Libros(String nombre_pdf, String ruta_pdf, String favorito, Long ultima_pagina, Usuarios usuario, Categorias categoria) {
        this.nombre_pdf = nombre_pdf;
        this.ruta_pdf = ruta_pdf;
        this.favorito = favorito;
        this.ultima_pagina = ultima_pagina;
        this.usuario = usuario;
        this.categoria = categoria;
    }

    public Long getId_libro() {
        return id_libro;
    }

    public void setId_libro(Long id_libro) {
        this.id_libro = id_libro;
    }

    public String getNombre_pdf() {
        return nombre_pdf;
    }

    public void setNombre_pdf(String nombre_pdf) {
        this.nombre_pdf = nombre_pdf;
    }

    public String getRuta_pdf() {
        return ruta_pdf;
    }

    public void setRuta_pdf(String ruta_pdf) {
        this.ruta_pdf = ruta_pdf;
    }

    public String getFavorito() {
        return favorito;
    }

    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }

    public Long getUltima_pagina() {
        return ultima_pagina;
    }

    public void setUltima_pagina(Long ultima_pagina) {
        this.ultima_pagina = ultima_pagina;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }
}
