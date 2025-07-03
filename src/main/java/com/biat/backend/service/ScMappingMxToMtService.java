package com.biat.backend.service;

import com.biat.backend.model.ScMappingMxToMt;
import com.biat.backend.repository.ScMappingMxToMtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScMappingMxToMtService {

    private final ScMappingMxToMtRepository repository;

    public List<ScMappingMxToMt> getAll() {
        return repository.findAll();
    }

    public ScMappingMxToMt getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mapping not found with id: " + id));
    }

    public ScMappingMxToMt create(ScMappingMxToMt entity) {
        // Add any validation or preprocessing here if needed
        return repository.save(entity);
    }

    public ScMappingMxToMt update(Long id, ScMappingMxToMt entity) {
        return repository.findById(id).map(existing -> {
            if (entity.getTag() != null && !entity.getTag().isEmpty()) {
                existing.setTag(entity.getTag());
            }
            if (entity.getFunction() != null && !entity.getFunction().isEmpty()) {
                existing.setFunction(entity.getFunction());
            }
            if (entity.getPath() != null && !entity.getPath().isEmpty()) {
                existing.setPath(entity.getPath());
            }
            if (entity.getAttribut() != null && !entity.getAttribut().isEmpty()) {
                existing.setAttribut(entity.getAttribut());
            }
            if (entity.getMsgType() != null && !entity.getMsgType().isEmpty()) {
                existing.setMsgType(entity.getMsgType());
            }
            if (entity.getSens() != null && !entity.getSens().isEmpty()) {
                existing.setSens(entity.getSens());
            }
            if (entity.getOrdre() != null && !entity.getOrdre().isEmpty()) {
                existing.setOrdre(entity.getOrdre());
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
