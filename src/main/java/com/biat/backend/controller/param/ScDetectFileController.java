package com.biat.backend.controller.param;

import com.biat.backend.model.param.ScDetectFile;
import com.biat.backend.service.param.ScDetectFileService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-detectfile")
@RequiredArgsConstructor
public class ScDetectFileController {

    private final ScDetectFileService service;

    @GetMapping
    public List<ScDetectFile> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ScDetectFile getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ScDetectFile create(@RequestBody ScDetectFile file) {
        return service.save(file);
    }

    @PutMapping("/{id}")
    public ScDetectFile update(@PathVariable Long id, @RequestBody ScDetectFile file) {
        return service.update(id, file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
