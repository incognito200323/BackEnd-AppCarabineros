package com.example.backend_esfocar.repository;

import com.example.backend_esfocar.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByRutAndContrasenaAndActivoTrue(String rut, String contrasena);
}