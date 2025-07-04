package com.biat.backend.service.MT;

import com.biat.backend.model.MT.Ns_Mt_File;
import com.biat.backend.repository.MT.ScNsMtRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScNsMtService {

    private final ScNsMtRepository repository;

    public List<Ns_Mt_File> getAll() {
        return repository.findAll();
    }

    public Ns_Mt_File getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }
}
