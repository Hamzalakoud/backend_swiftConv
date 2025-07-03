package com.biat.backend.controller.param;

import com.biat.backend.model.param.ScParamGlobal;
import com.biat.backend.service.param.ScParamGlobalService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-param-global")
@RequiredArgsConstructor
public class ScParamGlobalController {

    private final ScParamGlobalService service;

    @GetMapping
    public List<ScParamGlobal> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ScParamGlobal getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ScParamGlobal create(@RequestBody ScParamGlobal entity) {
        return service.create(entity);
    }

    @PutMapping("/{id}")
    public ScParamGlobal update(@PathVariable Long id, @RequestBody ScParamGlobal entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
