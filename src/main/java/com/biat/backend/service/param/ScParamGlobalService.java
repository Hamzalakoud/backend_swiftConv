package com.biat.backend.service.param;

import com.biat.backend.model.param.ScParamGlobal;
import com.biat.backend.repository.param.ScParamGlobalRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScParamGlobalService {

    private final ScParamGlobalRepository repository;

    public List<ScParamGlobal> getAll() {
        return repository.findAll();
    }

    public ScParamGlobal getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }

    public ScParamGlobal create(ScParamGlobal entity) {
        return repository.save(entity);
    }

    public ScParamGlobal update(Long id, ScParamGlobal entity) {
        ScParamGlobal existing = getById(id);
        existing.setElement(entity.getElement());
        existing.setValeur(entity.getValeur());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
