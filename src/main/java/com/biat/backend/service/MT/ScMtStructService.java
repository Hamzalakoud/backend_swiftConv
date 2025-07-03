package com.biat.backend.service.MT;

import com.biat.backend.model.MT.ScMtStruct;
import com.biat.backend.repository.MT.ScMtStructRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScMtStructService {

    private final ScMtStructRepository repository;

    public List<ScMtStruct> getAll() {
        return repository.findAll();
    }

    public ScMtStruct getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }

    public ScMtStruct create(ScMtStruct entity) {
        return repository.save(entity);
    }

    public ScMtStruct update(Long id, ScMtStruct entity) {
        ScMtStruct existing = getById(id);
        existing.setBodyTag(entity.getBodyTag());
        existing.setOrdre(entity.getOrdre());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
