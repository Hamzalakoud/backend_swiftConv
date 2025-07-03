package com.biat.backend.controller.MT;

import com.biat.backend.model.MT.ScMtFile;
import com.biat.backend.repository.MT.ScMtFileRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-mt-file")
@RequiredArgsConstructor
public class ScMtFileController {

    private final ScMtFileRepository repository;

    // Get all records
    @GetMapping
    public List<ScMtFile> getAll() {
        return repository.findAll();
    }

    // Get by ID
    @GetMapping("/{id}")
    public ScMtFile getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    // Get by currency
    @GetMapping("/currency/{currency}")
    public List<ScMtFile> getByCurrency(@PathVariable String currency) {
        return repository.findByCurrency(currency);
    }

    // Get by customer account
    @GetMapping("/account/{customerAccount}")
    public List<ScMtFile> getByCustomerAccount(@PathVariable String customerAccount) {
        return repository.findByCustomerAccount(customerAccount);
    }

    // Get by filename
    @GetMapping("/filename/{filename}")
    public List<ScMtFile> getByFilename(@PathVariable String filename) {
        return repository.findByFilename(filename);
    }

    // Get by message category
    @GetMapping("/msg-categ/{msgCateg}")
    public List<ScMtFile> getByMsgCateg(@PathVariable String msgCateg) {
        return repository.findByMsgCateg(msgCateg);
    }

    // Get by message type
    @GetMapping("/msg-type/{msgType}")
    public List<ScMtFile> getByMsgType(@PathVariable String msgType) {
        return repository.findByMsgType(msgType);
    }

    // Get by tag71A
    @GetMapping("/tag71a/{tag71A}")
    public List<ScMtFile> getByTag71A(@PathVariable String tag71A) {
        return repository.findByTag71A(tag71A);
    }
}
