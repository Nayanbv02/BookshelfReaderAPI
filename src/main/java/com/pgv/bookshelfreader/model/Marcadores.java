package com.pgv.bookshelfreader.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "marcadores")
public class Marcadores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_marcador;

    private Long numero_pagina;
    private String color;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_libro", referencedColumnName = "id_libro", nullable = false)
    private Libros libro;

    public Marcadores() {
    }

    public Marcadores(String color, Long numero_pagina, Date fecha, Libros libro) {
        this.color = color;
        this.numero_pagina = numero_pagina;
        this.fecha = fecha;
        this.libro = libro;
    }

    public Long getId_marcador() {
        return id_marcador;
    }

    public void setId_marcador(Long id_marcador) {
        this.id_marcador = id_marcador;
    }

    public Long getNumero_pagina() {
        return numero_pagina;
    }

    public void setNumero_pagina(Long numero_pagina) {
        this.numero_pagina = numero_pagina;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }
}