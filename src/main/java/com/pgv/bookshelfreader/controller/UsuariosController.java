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
import com.pgv.bookshelfreader.model.Usuarios;
import com.pgv.bookshelfreader.repository.UsuariosRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @GetMapping
    public List<Usuarios> obtenerTodosLosUsuarios() {
        return usuariosRepository.findAll();
    }

    @PostMapping
    public Usuarios crearUsuario(@RequestBody Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuarios obtenerUsuarioPorId(@PathVariable Long id) {
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @PutMapping("/{id}")
    public Usuarios actualizarUsuario(@PathVariable Long id, @RequestBody Usuarios detallesUsuario) {
        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        usuario.setNombre(detallesUsuario.getNombre());
        usuario.setCorreo(detallesUsuario.getCorreo());
        usuario.setClave(detallesUsuario.getClave());

        return usuariosRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuariosRepository.deleteById(id);
    }
}