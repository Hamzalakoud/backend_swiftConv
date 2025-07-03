package com.biat.backend.service.MT;

import com.biat.backend.model.MT.ScMtFile;
import com.biat.backend.repository.MT.ScMtFileRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScMtFileService {

    private final ScMtFileRepository repository;

    public ScMtFile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    public List<ScMtFile> getByCurrency(String currency) {
        List<ScMtFile> files = repository.findByCurrency(currency);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with currency: " + currency);
        }
        return files;
    }

    public List<ScMtFile> getByCustomerAccount(String customerAccount) {
        List<ScMtFile> files = repository.findByCustomerAccount(customerAccount);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with account: " + customerAccount);
        }
        return files;
    }

    public List<ScMtFile> getByFilename(String filename) {
        List<ScMtFile> files = repository.findByFilename(filename);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with filename: " + filename);
        }
        return files;
    }

    public List<ScMtFile> getByMsgCateg(String msgCateg) {
        List<ScMtFile> files = repository.findByMsgCateg(msgCateg);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with msgCateg: " + msgCateg);
        }
        return files;
    }

    public List<ScMtFile> getByMsgType(String msgType) {
        List<ScMtFile> files = repository.findByMsgType(msgType);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with msgType: " + msgType);
        }
        return files;
    }

    public List<ScMtFile> getByTag71A(String tag71A) {
        List<ScMtFile> files = repository.findByTag71A(tag71A);
        if (files.isEmpty()) {
            throw new RuntimeException("No files found with tag71A: " + tag71A);
        }
        return files;
    }

    public List<ScMtFile> getAll() {
        return repository.findAll();
    }
}
