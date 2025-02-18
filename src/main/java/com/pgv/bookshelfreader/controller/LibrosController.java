package com.pgv.bookshelfreader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgv.bookshelfreader.ResourceNotFoundException;
import com.pgv.bookshelfreader.model.Libros;
import com.pgv.bookshelfreader.model.Usuarios;
import com.pgv.bookshelfreader.repository.LibrosRepository;
import com.pgv.bookshelfreader.repository.UsuariosRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/libros")
public class LibrosController {

    @Autowired
    private LibrosRepository librosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @GetMapping
    public List<Libros> obtenerTodosLosLibros() {
        return librosRepository.findAll();
    }

    @PostMapping("/usuario/{usuarioId}")
    public Libros crearLibro(@PathVariable Long usuarioId, @RequestBody Libros libro) {

        Usuarios usuario = usuariosRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + usuarioId));

        libro.setUsuario(usuario);

        return librosRepository.save(libro);
    }

    @GetMapping("/{id_libro}")
    public Libros obtenerLibroPorId(@PathVariable Long id_libro) {
        return librosRepository.findById(id_libro)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));
    }

    @PutMapping("/{id_libro}")
    public Libros actualizarLibro(@PathVariable Long id_libro, @RequestBody Libros detallesLibro) {
        Libros libro = librosRepository.findById(id_libro)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));

        libro.setNombre_pdf(detallesLibro.getNombre_pdf());
        libro.setRuta_pdf(detallesLibro.getRuta_pdf());
        libro.setFavorito(detallesLibro.getFavorito());
        libro.setUltima_pagina(detallesLibro.getUltima_pagina());

        return librosRepository.save(libro);
    }

    @DeleteMapping("/{id_libro}")
    public void eliminarLibro(@PathVariable Long id_libro) {
        librosRepository.findById(id_libro)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));
        librosRepository.deleteById(id_libro);
    }
}
