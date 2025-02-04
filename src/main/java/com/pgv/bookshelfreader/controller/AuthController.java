package com.pgv.bookshelfreader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgv.bookshelfreader.model.Usuarios;
import com.pgv.bookshelfreader.repository.UsuariosRepository;
import com.pgv.bookshelfreader.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Usuarios usuario = usuariosRepository.findByNombre(loginRequest.nombre);
        if (usuario != null && passwordEncoder.matches(loginRequest.clave, usuario.getClave())) { // ✅ Comparar con
                                                                                                  // BCrypt
            return jwtUtil.generateToken(usuario.getNombre());
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        Usuarios usuario = new Usuarios();
        usuario.setNombre(registerRequest.nombre);
        usuario.setClave(passwordEncoder.encode(registerRequest.clave));
        usuario.setCorreo(registerRequest.correo);
        usuariosRepository.save(usuario);
        return "Usuario registrado";
    }

    // Clases internas para las solicitudes de login y registro
    public static class LoginRequest {
        public String nombre;
        public String clave;
    }

    public static class RegisterRequest {
        public String nombre;
        public String clave;
        public String correo;
    }
}