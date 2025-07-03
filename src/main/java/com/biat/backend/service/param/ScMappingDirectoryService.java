package com.biat.backend.service.param;

import com.biat.backend.model.param.ScMappingDirectory;
import com.biat.backend.repository.param.ScMappingDirectoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScMappingDirectoryService {

    private final ScMappingDirectoryRepository repository;

    public ScMappingDirectory getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mapping directory not found with id: " + id));
    }

    public List<ScMappingDirectory> getAll() {
        return repository.findAll();
    }

    public ScMappingDirectory create(ScMappingDirectory entity) {
        return repository.save(entity);
    }

    public ScMappingDirectory update(Long id, ScMappingDirectory updated) {
        ScMappingDirectory existing = getById(id);
        existing.setRepIn(updated.getRepIn());
        existing.setRepOut(updated.getRepOut());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }
}
