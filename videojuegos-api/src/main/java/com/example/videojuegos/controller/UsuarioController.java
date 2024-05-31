package com.example.videojuegos.controller;

import com.example.videojuegos.entity.Usuario;
import com.example.videojuegos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());
        Map<String, Object> response = new HashMap<>();
        if (usuarioExistente.isPresent()) {
            response.put("message", "El nombre de usuario ya existe.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        Usuario savedUsuario = usuarioRepository.save(usuario);
        response.put("message", "Usuario creado exitosamente.");
        response.put("usuario", savedUsuario);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUsuario(@RequestBody Usuario usuario) {
        boolean usernameExists = usuarioRepository.findByUsername(usuario.getUsername()).isPresent();
        boolean emailExists = usuarioRepository.findByEmail(usuario.getEmail()).isPresent();
        Map<String, Object> response = new HashMap<>();
        
        if (usernameExists || emailExists) {
            response.put("message", "El nombre de usuario o el correo electrónico ya están registrados.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        Usuario savedUsuario = usuarioRepository.save(usuario);
        response.put("message", "Usuario registrado exitosamente.");
        response.put("usuario", savedUsuario);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());
        Map<String, Object> response = new HashMap<>();
        if (usuarioExistente.isPresent() && usuario.getPassword().equals(usuarioExistente.get().getPassword())) {
         response.put("message", "Inicio de sesión exitoso.");
         return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
         response.put("message", "Nombre de usuario o contraseña incorrectos.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}


    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("usuario", usuario);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Usuario no encontrado.");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                });
    }
}