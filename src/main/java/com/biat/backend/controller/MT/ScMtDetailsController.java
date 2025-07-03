package com.biat.backend.controller.MT;

import com.biat.backend.model.MT.ScMtDetails;
import com.biat.backend.service.MT.ScMtDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-mt-details")
@RequiredArgsConstructor
public class ScMtDetailsController {

    private final ScMtDetailsService service;

    @GetMapping
    public List<ScMtDetails> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ScMtDetails getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
