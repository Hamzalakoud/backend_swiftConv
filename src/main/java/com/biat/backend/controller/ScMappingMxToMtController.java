package com.biat.backend.controller;

import com.biat.backend.model.ScMappingMxToMt;
import com.biat.backend.service.ScMappingMxToMtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-mapping-mx-to-mt")
@RequiredArgsConstructor
public class ScMappingMxToMtController {

    private final ScMappingMxToMtService service;

    @GetMapping
    public ResponseEntity<List<ScMappingMxToMt>> getAll() {
        List<ScMappingMxToMt> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScMappingMxToMt> getById(@PathVariable Long id) {
        try {
            ScMappingMxToMt entity = service.getById(id);
            return ResponseEntity.ok(entity);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ScMappingMxToMt> create(@RequestBody ScMappingMxToMt entity) {
        ScMappingMxToMt saved = service.create(entity);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScMappingMxToMt> update(@PathVariable Long id, @RequestBody ScMappingMxToMt entity) {
        try {
            ScMappingMxToMt updated = service.update(id, entity);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
