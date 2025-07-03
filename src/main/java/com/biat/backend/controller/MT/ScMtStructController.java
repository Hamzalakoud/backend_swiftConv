package com.biat.backend.controller.MT;

import com.biat.backend.model.MT.ScMtStruct;
import com.biat.backend.service.MT.ScMtStructService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-mt-struct")
@RequiredArgsConstructor
public class ScMtStructController {

    private final ScMtStructService service;

    @GetMapping
    public List<ScMtStruct> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ScMtStruct getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ScMtStruct create(@RequestBody ScMtStruct entity) {
        return service.create(entity);
    }

    @PutMapping("/{id}")
    public ScMtStruct update(@PathVariable Long id, @RequestBody ScMtStruct entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
