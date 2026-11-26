package com.example.backend_esfocar.service;

import com.example.backend_esfocar.model.Documento;
import com.example.backend_esfocar.repository.DocumentoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<Documento> listar() {
        return documentoRepository.findAllByOrderByIdDesc();
    }

    public Documento crear(Documento documento) {
        documento.setId(null);
        return documentoRepository.save(documento);
    }

    public boolean eliminar(Integer id) {
        if (!documentoRepository.existsById(id)) {
            return false;
        }
        documentoRepository.deleteById(id);
        return true;
    }
}