package com.example.backend_esfocar.controller;

import com.example.backend_esfocar.model.Documento;
import com.example.backend_esfocar.service.DocumentoService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping
    public List<Documento> listar() {
        return documentoService.listar();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Documento documento) {
        if (documento.getTitulo() == null || documento.getTitulo().isBlank()
                || documento.getContenido() == null || documento.getContenido().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Título y contenido son obligatorios"));
        }
        Documento guardado = documentoService.crear(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (!documentoService.eliminar(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Documento no encontrado"));
        }
        return ResponseEntity.noContent().build();
    }
}