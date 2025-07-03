package com.biat.backend.service.MX;

import com.biat.backend.model.MX.ScNsMx;
import com.biat.backend.repository.MX.ScNsMxRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScNsMxService {

    private final ScNsMxRepository scNsMxRepository;

    public List<ScNsMx> getAll() {
        return scNsMxRepository.findAll();
    }

    public ScNsMx getById(Long id) {
        return scNsMxRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }
}
