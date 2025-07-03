package com.biat.backend.service.MT;

import com.biat.backend.model.MT.ScNsMt;
import com.biat.backend.repository.MT.ScNsMtRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScNsMtService {

    private final ScNsMtRepository repository;

    public List<ScNsMt> getAll() {
        return repository.findAll();
    }

    public ScNsMt getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }
}
