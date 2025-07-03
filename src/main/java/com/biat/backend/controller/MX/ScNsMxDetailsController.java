package com.biat.backend.controller.MX;

import com.biat.backend.model.MX.ScNsMxDetails;
import com.biat.backend.service.MX.ScNsMxDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-ns-mx-details")
@RequiredArgsConstructor
public class ScNsMxDetailsController {

    private final ScNsMxDetailsService scNsMxDetailsService;

    @GetMapping
    public List<ScNsMxDetails> getAll() {
        return scNsMxDetailsService.getAll();
    }

    @GetMapping("/{id}")
    public ScNsMxDetails getById(@PathVariable Long id) {
        return scNsMxDetailsService.getById(id);
    }
}
