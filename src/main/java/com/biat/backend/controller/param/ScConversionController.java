package com.biat.backend.controller.param;

import com.biat.backend.model.param.ScConversion;
import com.biat.backend.repository.param.ScConversionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-conversion")
@RequiredArgsConstructor
public class ScConversionController {

    private final ScConversionRepository repository;

    // GET all conversion records
    @GetMapping
    public List<ScConversion> getAll() {
        return repository.findAll();
    }

    // GET by ID
    @GetMapping("/{id}")
    public ScConversion getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversion record not found with ID: " + id));
    }

    // GET by currency
    @GetMapping("/currency/{currency}")
    public List<ScConversion> getByCurrency(@PathVariable String currency) {
        return repository.findByCurrency(currency);
    }

    // GET by customer account
    @GetMapping("/account/{customerAccount}")
    public List<ScConversion> getByCustomerAccount(@PathVariable String customerAccount) {
        return repository.findByCustomerAccount(customerAccount);
    }

    // GET by filename
    @GetMapping("/filename/{filename}")
    public List<ScConversion> getByFilename(@PathVariable String filename) {
        return repository.findByFilename(filename);
    }

    // GET by message category
    @GetMapping("/msg-categ/{msgCateg}")
    public List<ScConversion> getByMsgCateg(@PathVariable String msgCateg) {
        return repository.findByMsgCateg(msgCateg);
    }

    // GET by message type
    @GetMapping("/msg-type/{msgType}")
    public List<ScConversion> getByMsgType(@PathVariable String msgType) {
        return repository.findByMsgType(msgType);
    }

    // GET by tag71A
    @GetMapping("/tag71a/{tag71A}")
    public List<ScConversion> getByTag71A(@PathVariable String tag71A) {
        return repository.findByTag71A(tag71A);
    }

    // GET by status
    @GetMapping("/status/{status}")
    public List<ScConversion> getByStatus(@PathVariable String status) {
        return repository.findByStatus(status);
    }

    // GET by sens
    @GetMapping("/sens/{sens}")
    public List<ScConversion> getBySens(@PathVariable String sens) {
        return repository.findBySens(sens);
    }

    // GET by toConvert
    @GetMapping("/to-convert/{toConvert}")
    public List<ScConversion> getByToConvert(@PathVariable String toConvert) {
        return repository.findByToConvert(toConvert);
    }

    // GET total count
    @GetMapping("/count")
    public long countMessages() {
        return repository.count();
    }
}
