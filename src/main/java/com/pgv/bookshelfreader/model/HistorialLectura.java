package com.pgv.bookshelfreader.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_lectura")
public class HistorialLectura {

    @Id
    private Integer id_historial;

    private LocalDateTime fecha_lectura;

    private Integer duracion;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = true)
    private Libros libro;

    public HistorialLectura() {}

    public HistorialLectura(Integer id_historial, LocalDateTime fecha_lectura, Integer duracion, Libros libro) {
        this.id_historial = id_historial;
        this.fecha_lectura = fecha_lectura;
        this.duracion = duracion;
        this.libro = libro;
    }

    public Integer getId_historial() {
        return id_historial;
    }

    public void setId_historial(Integer id_historial) {
        this.id_historial = id_historial;
    }

    public LocalDateTime getFecha_lectura() {
        return fecha_lectura;
    }

    public void setFecha_lectura(LocalDateTime fecha_lectura) {
        this.fecha_lectura = fecha_lectura;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }
}
