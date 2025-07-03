package com.biat.backend.controller.MX;

import com.biat.backend.model.MX.ScMxFile;
import com.biat.backend.repository.MX.ScMxFileRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-mx-file")
@RequiredArgsConstructor
public class ScMxFileController {

    private final ScMxFileRepository repository;

    // Get all records
    @GetMapping
    public List<ScMxFile> getAll() {
        return repository.findAll();
    }

    // Get by ID
    @GetMapping("/{id}")
    public ScMxFile getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    // Get by currency
    @GetMapping("/currency/{currency}")
    public List<ScMxFile> getByCurrency(@PathVariable String currency) {
        return repository.findByCurrency(currency);
    }

    // Get by customer account
    @GetMapping("/account/{customerAccount}")
    public List<ScMxFile> getByCustomerAccount(@PathVariable String customerAccount) {
        return repository.findByCustomerAccount(customerAccount);
    }

    // Get by filename
    @GetMapping("/filename/{filename}")
    public List<ScMxFile> getByFilename(@PathVariable String filename) {
        return repository.findByFilename(filename);
    }

    // Get by message category
    @GetMapping("/msg-categ/{msgCateg}")
    public List<ScMxFile> getByMsgCateg(@PathVariable String msgCateg) {
        return repository.findByMsgCateg(msgCateg);
    }

    // Get by message type
    @GetMapping("/msg-type/{msgType}")
    public List<ScMxFile> getByMsgType(@PathVariable String msgType) {
        return repository.findByMsgType(msgType);
    }

    // Get by tag71A
    @GetMapping("/tag71a/{tag71A}")
    public List<ScMxFile> getByTag71A(@PathVariable String tag71A) {
        return repository.findByTag71A(tag71A);
    }
}
