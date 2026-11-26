package com.example.backend_esfocar.controller;

import com.example.backend_esfocar.model.Usuario;
import com.example.backend_esfocar.service.AuthService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/health")
    public ResponseEntity> health() {
        return ResponseEntity.ok(Map.of("status", "ok", "servicio", "esfocar-backend"));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.rut() == null || request.contrasena() == null
                || request.rut().isBlank() || request.contrasena().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Debes ingresar RUT y contraseña"));
        }

        return authService.login(request.rut(), request.contrasena())
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("mensaje", "RUT o contraseña incorrectos")));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        if (usuario.getRut() == null || usuario.getNombre() == null || usuario.getContrasena() == null
                || usuario.getRut().isBlank() || usuario.getNombre().isBlank() || usuario.getContrasena().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Todos los campos son obligatorios"));
        }

        return authService.registrar(usuario)
                .<ResponseEntity<?>>map(creado -> ResponseEntity.status(HttpStatus.CREATED).body(creado))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("mensaje", "Ya existe un estudiante registrado con ese RUT")));
    }

    public record LoginRequest(String rut, String contrasena) {
    }
}