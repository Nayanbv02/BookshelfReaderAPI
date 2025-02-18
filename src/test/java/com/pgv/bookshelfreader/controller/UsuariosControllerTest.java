package com.pgv.bookshelfreader.controller;

import com.pgv.bookshelfreader.model.Usuarios;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UsuariosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    private Usuarios usuario;

    @BeforeEach
    void setUp() {
        usuariosRepository.deleteAll();
        usuario = usuariosRepository.save(new Usuarios("usuarioTest", "correo@test.com", "claveTest"));
    }

    private String obtenerTokenJwt(String nombre, String clave) throws Exception {
        return jwtUtil.generateToken(nombre);
    }

    @Test
    public void testActualizarUsuario() throws Exception {
        Long usuarioId = usuario.getId();

        Usuarios usuarioActualizado = new Usuarios("NuevoNombre", "nuevo@correo.com", "nuevaClave");

        String usuarioJson = objectMapper.writeValueAsString(usuarioActualizado);

        String tokenJwt = obtenerTokenJwt(usuario.getNombre(), usuario.getClave());

        mockMvc.perform(put("/api/usuarios/" + usuarioId) // Esto no va (revisar)
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson)
                .header("Authorization", "Bearer " + tokenJwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("NuevoNombre"))
                .andExpect(jsonPath("$.correo").value("nuevo@correo.com"));
    }

}
