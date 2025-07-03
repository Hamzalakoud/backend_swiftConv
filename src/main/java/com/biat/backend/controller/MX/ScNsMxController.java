package com.biat.backend.controller.MX;

import com.biat.backend.model.MX.ScNsMx;
import com.biat.backend.service.MX.ScNsMxService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sc-ns-mx")
@RequiredArgsConstructor
public class ScNsMxController {

    private final ScNsMxService scNsMxService;

    @GetMapping
    public List<ScNsMx> getAll() {
        return scNsMxService.getAll();
    }

    @GetMapping("/{id}")
    public ScNsMx getById(@PathVariable Long id) {
        return scNsMxService.getById(id);
    }
}
