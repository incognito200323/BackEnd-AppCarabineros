package com.example.backend_esfocar;

import com.example.backend_esfocar.model.Usuario;
import com.example.backend_esfocar.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendEsfocarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendEsfocarApplication.class, args);
    }

    @Bean
    CommandLineRunner precargarDatos(UsuarioRepository usuarioRepository) {
        return args -> {
            if (!usuarioRepository.existsById("12345678-9")) {
                usuarioRepository.save(new Usuario("12345678-9", "Estudiante 1", "contrasena1", true));
            }
            if (!usuarioRepository.existsById("98765432-1")) {
                usuarioRepository.save(new Usuario("98765432-1", "Estudiante 2", "contrasena2", true));
            }
        };
    }
}