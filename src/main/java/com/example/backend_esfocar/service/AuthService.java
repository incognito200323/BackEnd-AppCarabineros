package com.example.backend_esfocar.service;

import com.example.backend_esfocar.model.Usuario;
import com.example.backend_esfocar.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> login(String rut, String contrasena) {
        return usuarioRepository.findByRutAndContrasenaAndActivoTrue(rut.trim(), contrasena.trim());
    }

    public Optional<Usuario> registrar(Usuario usuario) {
        String rutLimpio = usuario.getRut().trim();
        if (usuarioRepository.existsById(rutLimpio)) {
            return Optional.empty();
        }
        Usuario nuevo = new Usuario(
                rutLimpio,
                usuario.getNombre().trim(),
                usuario.getContrasena().trim(),
                true
        );
        return Optional.of(usuarioRepository.save(nuevo));
    }
}