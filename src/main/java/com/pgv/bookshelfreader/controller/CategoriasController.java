package com.pgv.bookshelfreader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pgv.bookshelfreader.ResourceNotFoundException;
import com.pgv.bookshelfreader.model.Categorias;
import com.pgv.bookshelfreader.repository.CategoriasRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping
    public List<Categorias> obtenerTodasLasCategorias() {
        return categoriasRepository.findAll();
    }

    @PostMapping
    public Categorias crearCategoria(@RequestBody Categorias categoria) {
        return categoriasRepository.save(categoria);
    }

    @GetMapping("/{id_categoria}")
    public Categorias obtenerCategoriaPorId(@PathVariable Integer id_categoria) {
        return categoriasRepository.findById(id_categoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
    }

    @PutMapping("/{id_categoria}")
    public Categorias actualizarCategoria(@PathVariable Integer id_categoria, @RequestBody Categorias detalles) {
        Categorias categoria = categoriasRepository.findById(id_categoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        categoria.setNombre_categoria(detalles.getNombre_categoria());

        return categoriasRepository.save(categoria);
    }

    @DeleteMapping("/{id_categoria}")
    public void eliminarCategoria(@PathVariable Integer id_categoria) {
        categoriasRepository.findById(id_categoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        categoriasRepository.deleteById(id_categoria);
    }
}
