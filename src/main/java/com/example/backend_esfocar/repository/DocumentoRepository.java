package com.example.backend_esfocar.repository;

import com.example.backend_esfocar.model.Documento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    List<Documento> findAllByOrderByIdDesc();
}