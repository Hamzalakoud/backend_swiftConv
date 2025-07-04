package com.biat.backend.controller.MT;

import com.biat.backend.model.MT.Ns_Mt_File;
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
    public List<Ns_Mt_File> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Ns_Mt_File getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
