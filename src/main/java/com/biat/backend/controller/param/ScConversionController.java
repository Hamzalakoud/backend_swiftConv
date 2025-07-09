package com.biat.backend.controller.param;

import com.biat.backend.dto.DashboardStatsDTO;
import com.biat.backend.model.param.ScConversion;
import com.biat.backend.repository.param.ScConversionRepository;
import com.biat.backend.service.param.ScConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sc-conversion")
@RequiredArgsConstructor
public class ScConversionController {

    private final ScConversionRepository repository;
    private final ScConversionService scConversionService;

    // === KEEPING all existing methods unchanged ===

    @GetMapping
    public ResponseEntity<List<ScConversion>> getAll() {
        List<ScConversion> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScConversion> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<ScConversion>> getFiltered(@RequestParam Map<String, String> params) {
        List<ScConversion> all = repository.findAll();

        List<ScConversion> filtered = all.stream()
            .filter(sc -> filenameMatches(sc.getFilename(), params.get("filename")))
            .filter(sc -> startsWithMatch(sc.getMsgCateg(), params.get("msgCateg")))
            .filter(sc -> startsWithMatch(sc.getMsgType(), params.get("msgType")))
            .filter(sc -> startsWithMatch(sc.getBicEm(), params.get("bicEm")))
            .filter(sc -> startsWithMatch(sc.getBicDe(), params.get("bicDe")))
            .filter(sc -> startsWithMatch(sc.getUertr(), params.get("uertr")))
            .filter(sc -> {
                String amountStr = params.get("amount");
                if (amountStr == null || amountStr.isEmpty()) return true;
                try {
                    double amountFilter = Double.parseDouble(amountStr);
                    return sc.getAmount() == amountFilter;
                } catch (NumberFormatException e) {
                    return true;
                }
            })
            .filter(sc -> startsWithMatch(sc.getCurrency(), params.get("currency")))
            .filter(sc -> startsWithMatch(sc.getCustomerAccount(), params.get("customerAccount")))
            .filter(sc -> startsWithMatch(sc.getTag71A(), params.get("tag71A")))
            .filter(sc -> startsWithMatch(sc.getStatus(), params.get("status")))
            .filter(sc -> startsWithMatch(sc.getSens(), params.get("sens")))
            .filter(sc -> startsWithMatch(sc.getToConvert(), params.get("toConvert")))
            .collect(Collectors.toList());

        return ResponseEntity.ok(filtered);
    }

    private boolean filterMatch(String fieldValue, String filterValue) {
        if (filterValue == null || filterValue.isEmpty()) {
            return true;
        }
        if (fieldValue == null) {
            return false;
        }
        return fieldValue.equalsIgnoreCase(filterValue);
    }

    private boolean filenameMatches(String filename, String filter) {
        if (filter == null || filter.isEmpty()) {
            return true;
        }
        if (filename == null || filename.isEmpty()) {
            return false;
        }

        String filterLower = filter.toLowerCase().replaceAll("\\.*$", "");
        String filenameLower = filename.toLowerCase();

        return filenameLower.startsWith(filterLower);
    }

    private boolean startsWithMatch(String fieldValue, String filterValue) {
        if (filterValue == null || filterValue.isEmpty()) {
            return true;
        }
        if (fieldValue == null || fieldValue.isEmpty()) {
            return false;
        }

        String filterLower = filterValue.toLowerCase().trim();
        String fieldLower = fieldValue.toLowerCase().trim();

        return fieldLower.startsWith(filterLower);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countMessages() {
        return ResponseEntity.ok(repository.count());
    }

    // ✅ UPDATED: Dashboard stats use service + DTO
    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        DashboardStatsDTO stats = scConversionService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }
}
