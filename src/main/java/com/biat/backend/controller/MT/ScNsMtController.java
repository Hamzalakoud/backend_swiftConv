package com.biat.backend.controller.MT;

import com.biat.backend.model.MT.ScNsMt;
import com.biat.backend.service.MT.ScNsMtService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-ns-mt")
@RequiredArgsConstructor
public class ScNsMtController {

    private final ScNsMtService service;

    @GetMapping
    public List<ScNsMt> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ScNsMt getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
