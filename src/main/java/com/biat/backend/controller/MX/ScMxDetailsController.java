package com.biat.backend.controller.MX;

import com.biat.backend.model.MX.ScMxDetails;
import com.biat.backend.service.MX.ScMxDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-mx-details")
@RequiredArgsConstructor
public class ScMxDetailsController {

    private final ScMxDetailsService scMxDetailsService;

    @GetMapping
    public List<ScMxDetails> getAll() {
        return scMxDetailsService.getAll();
    }

    @GetMapping("/{id}")
    public ScMxDetails getById(@PathVariable Long id) {
        return scMxDetailsService.getById(id);
    }
}
