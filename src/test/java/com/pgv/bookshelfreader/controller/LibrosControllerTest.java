package com.pgv.bookshelfreader.controller;

import com.pgv.bookshelfreader.model.Libros;
import com.pgv.bookshelfreader.model.Usuarios;
import com.pgv.bookshelfreader.repository.LibrosRepository;
import com.pgv.bookshelfreader.repository.UsuariosRepository;
import com.pgv.bookshelfreader.utils.JwtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LibrosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LibrosRepository librosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        librosRepository.deleteAll();
        usuariosRepository.deleteAll();

        // Usuario y libros de prueba
        Usuarios usuario = usuariosRepository.save(new Usuarios("usuarioTest", "correo@test.com", "claveTest"));

        librosRepository.save(new Libros("Libro1", "Ruta1", "0", 25L, usuario));
        librosRepository.save(new Libros("Libro2", "Ruta2", "1", 72L, usuario));
    }

    private String obtenerTokenJwt(String nombre, String clave) throws Exception {
        return jwtUtil.generateToken(nombre);
    }

    @Test
    void getAllLibros_shouldReturnListOfLibros() throws Exception {

        String token = obtenerTokenJwt("usuarioTest", "claveTest");

        // Get de libros pasando el token para autenticacion
        mockMvc.perform(get("/api/libros")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre_pdf").exists())
                .andExpect(jsonPath("$[1].nombre_pdf").exists());
    }

}
