package com.biat.backend.service.MX;

import com.biat.backend.model.MX.ScNsMxDetails;
import com.biat.backend.repository.MX.ScNsMxDetailsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScNsMxDetailsService {

    private final ScNsMxDetailsRepository scNsMxDetailsRepository;

    public List<ScNsMxDetails> getAll() {
        return scNsMxDetailsRepository.findAll();
    }

    public ScNsMxDetails getById(Long id) {
        return scNsMxDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }
}
