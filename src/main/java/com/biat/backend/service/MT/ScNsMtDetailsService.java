package com.biat.backend.service.MT;

import com.biat.backend.model.MT.ScNsMtDetails;
import com.biat.backend.repository.MT.ScNsMtDetailsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScNsMtDetailsService {

    private final ScNsMtDetailsRepository repository;

    public List<ScNsMtDetails> getAll() {
        return repository.findAll();
    }

    public ScNsMtDetails getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }
}
