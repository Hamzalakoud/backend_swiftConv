package com.biat.backend.service;

import com.biat.backend.model.ScMappingMtToMx;
import com.biat.backend.repository.ScMappingMtToMxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScMappingMtToMxService {

    private final ScMappingMtToMxRepository repository;

    public List<ScMappingMtToMx> getAll() {
        return repository.findAll();
    }

    public ScMappingMtToMx getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mapping not found with id: " + id));
    }

    public ScMappingMtToMx create(ScMappingMtToMx entity) {
        // Add any validation or preprocessing here if needed
        return repository.save(entity);
    }

    public ScMappingMtToMx update(Long id, ScMappingMtToMx entity) {
        return repository.findById(id).map(existing -> {
            if (entity.getMtType() != null && !entity.getMtType().isEmpty()) {
                existing.setMtType(entity.getMtType());
            }
            if (entity.getMtTag() != null && !entity.getMtTag().isEmpty()) {
                existing.setMtTag(entity.getMtTag());
            }
            if (entity.getAttribut() != null && !entity.getAttribut().isEmpty()) {
                existing.setAttribut(entity.getAttribut());
            }
            if (entity.getMxType() != null && !entity.getMxType().isEmpty()) {
                existing.setMxType(entity.getMxType());
            }
            if (entity.getMxPath() != null && !entity.getMxPath().isEmpty()) {
                existing.setMxPath(entity.getMxPath());
            }
            if (entity.getConvFunc() != null && !entity.getConvFunc().isEmpty()) {
                existing.setConvFunc(entity.getConvFunc());
            }
            if (entity.getNiveau() != null && !entity.getNiveau().isEmpty()) {
                existing.setNiveau(entity.getNiveau());
            }
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Mapping not found with id: " + id));
    }


    public String delete(Long id) {
        return repository.findById(id).map(existing -> {
            repository.delete(existing);
            return "Mapping deleted successfully with id: " + id;
        }).orElseThrow(() -> new RuntimeException("Mapping not found with id: " + id));
    }
}
