package com.biat.backend.controller.param;

import com.biat.backend.model.param.ScMappingDirectory;
import com.biat.backend.service.param.ScMappingDirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-mapping-directory")
@RequiredArgsConstructor
public class ScMappingDirectoryController {

    private final ScMappingDirectoryService service;

    @GetMapping
    public List<ScMappingDirectory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ScMappingDirectory getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ScMappingDirectory create(@RequestBody ScMappingDirectory entity) {
        return service.create(entity);
    }

    @PutMapping("/{id}")
    public ScMappingDirectory update(@PathVariable Long id, @RequestBody ScMappingDirectory entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }
}
