package com.biat.backend.service.MT;

import com.biat.backend.model.MT.ScMtDetails;
import com.biat.backend.repository.MT.ScMtDetailsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScMtDetailsService {

    private final ScMtDetailsRepository repository;

    public List<ScMtDetails> getAll() {
        return repository.findAll();
    }

    public ScMtDetails getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }
}
