package com.biat.backend.service.param;

import com.biat.backend.model.param.ScDetectFile;
import com.biat.backend.repository.param.ScDetectFileRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScDetectFileService {

    private final ScDetectFileRepository repository;

    public List<ScDetectFile> getAll() {
        return repository.findAll();
    }

    public ScDetectFile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }

    public ScDetectFile save(ScDetectFile file) {
        return repository.save(file);
    }

    public ScDetectFile update(Long id, ScDetectFile updatedFile) {
        ScDetectFile file = getById(id);
        file.setFilename(updatedFile.getFilename());
        file.setSize(updatedFile.getSize());
        file.setStatus(updatedFile.getStatus());
        file.setCurrentTask(updatedFile.getCurrentTask());
        file.setFilePath(updatedFile.getFilePath());
        return repository.save(file);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
