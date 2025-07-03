package com.biat.backend.controller;

import com.biat.backend.model.ScMappingMtToMx;
import com.biat.backend.service.ScMappingMtToMxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-mapping-mt-to-mx")
@RequiredArgsConstructor
public class ScMappingMtToMxController {

    private final ScMappingMtToMxService service;

    @GetMapping
    public ResponseEntity<List<ScMappingMtToMx>> getAll() {
        List<ScMappingMtToMx> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScMappingMtToMx> getById(@PathVariable Long id) {
        try {
            ScMappingMtToMx entity = service.getById(id);
            return ResponseEntity.ok(entity);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ScMappingMtToMx> create(@RequestBody ScMappingMtToMx entity) {
        ScMappingMtToMx saved = service.create(entity);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScMappingMtToMx> update(@PathVariable Long id, @RequestBody ScMappingMtToMx entity) {
        try {
            ScMappingMtToMx updated = service.update(id, entity);
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
