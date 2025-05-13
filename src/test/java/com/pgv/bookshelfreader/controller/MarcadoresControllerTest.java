package com.pgv.bookshelfreader.controller;

import com.pgv.bookshelfreader.model.Libros;
import com.pgv.bookshelfreader.model.Marcadores;
import com.pgv.bookshelfreader.model.Usuarios;
import com.pgv.bookshelfreader.repository.LibrosRepository;
import com.pgv.bookshelfreader.repository.MarcadoresRepository;
import com.pgv.bookshelfreader.repository.UsuariosRepository;
import com.pgv.bookshelfreader.utils.JwtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MarcadoresControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MarcadoresRepository marcadoresRepository;

    @Autowired
    private LibrosRepository librosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private Marcadores marcador;
    private Usuarios usuario;

    @BeforeEach
    void setUp() throws ParseException {
        marcadoresRepository.deleteAll();
        librosRepository.deleteAll();
        usuariosRepository.deleteAll();

        usuario = usuariosRepository.save(new Usuarios("usuarioTest", "correo@test.com", "claveTest"));
        Libros libro = librosRepository.save(new Libros("Libro Test", "ruta/test", "0", 100L, usuario, null));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = dateFormat.parse("2024-02-18");

        marcador = marcadoresRepository.save(new Marcadores("Rojo", 10L, fecha, libro));
    }

    private String obtenerTokenJwt(String nombre, String clave) throws Exception {
        return jwtUtil.generateToken(nombre);
    }

    @Test
void createMarcador_shouldCreateMarcador() throws Exception {
    String token = obtenerTokenJwt("usuarioTest", "claveTest");

    Long idLibro = marcador.getLibro().getId_libro();

    String marcadorJson = "{ \"color\": \"Azul\", \"numero_pagina\": 20, \"fecha\": \"2024-02-18\" }";

    mockMvc.perform(post("/api/marcadores/libro/" + idLibro) // Lo mismo que el test de Usuarios (revisar)
            .header("Authorization", "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(marcadorJson))
            .andExpect(status().isOk());
}

}
