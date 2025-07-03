package com.biat.backend.service.MX;

import com.biat.backend.model.MX.ScMxFile;
import com.biat.backend.repository.MX.ScMxFileRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScMxFileService {

    private final ScMxFileRepository repository;

    public ScMxFile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    public List<ScMxFile> getByCurrency(String currency) {
        List<ScMxFile> files = repository.findByCurrency(currency);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with currency: " + currency);
        }
        return files;
    }

    public List<ScMxFile> getByCustomerAccount(String customerAccount) {
        List<ScMxFile> files = repository.findByCustomerAccount(customerAccount);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with account: " + customerAccount);
        }
        return files;
    }

    public List<ScMxFile> getByFilename(String filename) {
        List<ScMxFile> files = repository.findByFilename(filename);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with filename: " + filename);
        }
        return files;
    }

    public List<ScMxFile> getByMsgCateg(String msgCateg) {
        List<ScMxFile> files = repository.findByMsgCateg(msgCateg);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with msgCateg: " + msgCateg);
        }
        return files;
    }

    public List<ScMxFile> getByMsgType(String msgType) {
        List<ScMxFile> files = repository.findByMsgType(msgType);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with msgType: " + msgType);
        }
        return files;
    }

    public List<ScMxFile> getByTag71A(String tag71A) {
        List<ScMxFile> files = repository.findByTag71A(tag71A);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with tag71A: " + tag71A);
        }
        return files;
    }

    public List<ScMxFile> getAll() {
        return repository.findAll();
    }
}
