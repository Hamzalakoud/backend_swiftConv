package com.biat.backend.service.MX;

import com.biat.backend.model.MX.ScMxDetails;
import com.biat.backend.repository.MX.ScMxDetailsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScMxDetailsService {

    private final ScMxDetailsRepository scMxDetailsRepository;

    public List<ScMxDetails> getAll() {
        return scMxDetailsRepository.findAll();
    }

    public ScMxDetails getById(Long id) {
        return scMxDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }
}
