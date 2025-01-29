package com.pgv.bookshelfreader.controller;

import com.pgv.bookshelfreader.model.Marcadores;
import com.pgv.bookshelfreader.model.Libros;
import com.pgv.bookshelfreader.repository.MarcadoresRepository;
import com.pgv.bookshelfreader.repository.LibrosRepository;
import com.pgv.bookshelfreader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/marcadores")
public class MarcadoresController {

    @Autowired
    private MarcadoresRepository marcadoresRepository;
    
    @Autowired
    private LibrosRepository librosRepository;

    @GetMapping
    public List<Marcadores> obtenerTodosLosMarcadores() {
        return marcadoresRepository.findAll();
    }

    @PostMapping("/libro/{id_libro}")
    public Marcadores crearMarcador(@PathVariable Long id_libro, @RequestBody Marcadores marcador) {
        Libros libro = librosRepository.findById(id_libro)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con el id: " + id_libro));
        marcador.setLibro(libro);
        return marcadoresRepository.save(marcador);
    }

    @GetMapping("/{id_marcador}")
    public Marcadores obtenerMarcadorPorId(@PathVariable Long id_marcador) {
        return marcadoresRepository.findById(id_marcador)
                .orElseThrow(() -> new ResourceNotFoundException("Marcador no encontrado"));
    }

    @PutMapping("/{id_marcador}")
    public Marcadores actualizarMarcador(@PathVariable Long id_marcador, @RequestBody Marcadores detallesMarcador) {
        Marcadores marcador = marcadoresRepository.findById(id_marcador)
                .orElseThrow(() -> new ResourceNotFoundException("Marcador no encontrado"));

        marcador.setNumero_pagina(detallesMarcador.getNumero_pagina());
        marcador.setColor(detallesMarcador.getColor());
        marcador.setFecha(detallesMarcador.getFecha());
        
        return marcadoresRepository.save(marcador);
    }

    @DeleteMapping("/{id_marcador}")
    public void eliminarMarcador(@PathVariable Long id_marcador) {
        marcadoresRepository.findById(id_marcador)
                .orElseThrow(() -> new ResourceNotFoundException("Marcador no encontrado"));
        marcadoresRepository.deleteById(id_marcador);
    }
}