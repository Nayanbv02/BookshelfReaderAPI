package com.pgv.bookshelfreader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pgv.bookshelfreader.ResourceNotFoundException;
import com.pgv.bookshelfreader.model.HistorialLectura;
import com.pgv.bookshelfreader.model.Libros;
import com.pgv.bookshelfreader.repository.HistorialLecturaRepository;
import com.pgv.bookshelfreader.repository.LibrosRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/historial")
public class HistorialLecturaController {

    @Autowired
    private HistorialLecturaRepository historialRepository;

    @Autowired
    private LibrosRepository librosRepository;

    @GetMapping
    public List<HistorialLectura> obtenerHistorial() {
        return historialRepository.findAll();
    }

    @PostMapping("/libro/{id_libro}")
    public HistorialLectura crearHistorial(@PathVariable Long id_libro, @RequestBody HistorialLectura historial) {
        Libros libro = librosRepository.findById(id_libro)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));
        historial.setLibro(libro);
        return historialRepository.save(historial);
    }

    @GetMapping("/{id_historial}")
    public HistorialLectura obtenerPorId(@PathVariable Integer id_historial) {
        return historialRepository.findById(id_historial)
                .orElseThrow(() -> new ResourceNotFoundException("Historial no encontrado"));
    }

    @PutMapping("/{id_historial}")
    public HistorialLectura actualizarHistorial(@PathVariable Integer id_historial, @RequestBody HistorialLectura detalles) {
        HistorialLectura historial = historialRepository.findById(id_historial)
                .orElseThrow(() -> new ResourceNotFoundException("Historial no encontrado"));

        historial.setFecha_lectura(detalles.getFecha_lectura());
        historial.setDuracion(detalles.getDuracion());

        return historialRepository.save(historial);
    }

    @DeleteMapping("/{id_historial}")
    public void eliminarHistorial(@PathVariable Integer id_historial) {
        historialRepository.findById(id_historial)
                .orElseThrow(() -> new ResourceNotFoundException("Historial no encontrado"));
        historialRepository.deleteById(id_historial);
    }
}
