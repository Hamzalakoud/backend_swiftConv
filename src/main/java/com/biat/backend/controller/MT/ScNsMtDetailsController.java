package com.biat.backend.controller.MT;

import com.biat.backend.model.MT.ScNsMtDetails;
import com.biat.backend.service.MT.ScNsMtDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-ns-mt-details")
@RequiredArgsConstructor
public class ScNsMtDetailsController {

    private final ScNsMtDetailsService service;

    @GetMapping
    public List<ScNsMtDetails> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ScNsMtDetails getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
