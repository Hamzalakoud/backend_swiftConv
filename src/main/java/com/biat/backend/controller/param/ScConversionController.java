package com.biat.backend.controller.param;

import com.biat.backend.model.param.ScConversion;
import com.biat.backend.repository.param.ScConversionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sc-conversion")
@RequiredArgsConstructor
public class ScConversionController {

    private final ScConversionRepository repository;

    // GET all conversion records
    @GetMapping
    public ResponseEntity<List<ScConversion>> getAll() {
        List<ScConversion> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<ScConversion> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Filtered GET with enhanced filename logic
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

    // Enhanced filename match: if filter is "file66" 
    // match filename exactly "file66" OR filename starting with "file66."
    private boolean filenameMatches(String filename, String filter) {
        if (filter == null || filter.isEmpty()) {
            return true;
        }
        if (filename == null || filename.isEmpty()) {
            return false;
        }

        String filterLower = filter.toLowerCase().replaceAll("\\.*$", ""); // remove trailing dots
        String filenameLower = filename.toLowerCase();

        // Instead of exact match or exact + ".", use startsWith filterLower
        return filenameLower.startsWith(filterLower);
    }

    private boolean startsWithMatch(String fieldValue, String filterValue) {
        if (filterValue == null || filterValue.isEmpty()) {
            return true; // no filter => accept all
        }
        if (fieldValue == null || fieldValue.isEmpty()) {
            return false; // field empty but filter not
        }

        // Normalize by trimming and lowercase comparison
        String filterLower = filterValue.toLowerCase().trim();
        String fieldLower = fieldValue.toLowerCase().trim();

        // Check if field starts with filter string
        return fieldLower.startsWith(filterLower);
    }


    @GetMapping("/count")
    public ResponseEntity<Long> countMessages() {
        return ResponseEntity.ok(repository.count());
    }

    // === New dashboard stats endpoint ===
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);

        List<ScConversion> all = repository.findAll();

        Predicate<ScConversion> isSameDay = sc -> sc.getCreationDate().isEqual(today);
        Predicate<ScConversion> isInWeek = sc -> {
            LocalDate creationDate = sc.getCreationDate();
            return (creationDate.isEqual(today) || (creationDate.isAfter(startOfWeek) && creationDate.isBefore(today.plusDays(1))));
        };

        long totalConvertedDay = all.stream()
                .filter(sc -> "CONVERTED".equalsIgnoreCase(sc.getStatus()) && isSameDay.test(sc))
                .count();
        long totalNSConvertDay = all.stream()
                .filter(sc -> "NC".equalsIgnoreCase(sc.getStatus()) && isSameDay.test(sc))
                .count();
        long totalFailedDay = all.stream()
                .filter(sc -> "FAILED".equalsIgnoreCase(sc.getStatus()) && isSameDay.test(sc))
                .count();

        long totalConvertedWeek = all.stream()
                .filter(sc -> "CONVERTED".equalsIgnoreCase(sc.getStatus()) && isInWeek.test(sc))
                .count();
        long totalNSConvertWeek = all.stream()
                .filter(sc -> "NC".equalsIgnoreCase(sc.getStatus()) && isInWeek.test(sc))
                .count();
        long totalFailedWeek = all.stream()
                .filter(sc -> "FAILED".equalsIgnoreCase(sc.getStatus()) && isInWeek.test(sc))
                .count();

        // Last 7 days data for charts
        List<Map<String, Object>> last7Days = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);

            long convertedCount = all.stream()
                    .filter(sc -> "CONVERTED".equalsIgnoreCase(sc.getStatus()) &&
                            sc.getCreationDate().isEqual(date))
                    .count();
            long nsCount = all.stream()
                    .filter(sc -> "NC".equalsIgnoreCase(sc.getStatus()) &&
                            sc.getCreationDate().isEqual(date))
                    .count();
            long failedCount = all.stream()
                    .filter(sc -> "FAILED".equalsIgnoreCase(sc.getStatus()) &&
                            sc.getCreationDate().isEqual(date))
                    .count();

            Map<String, Object> dayData = new HashMap<>();
            dayData.put("date", date.toString());
            dayData.put("converted", convertedCount);
            dayData.put("ns", nsCount);
            dayData.put("failed", failedCount);
            last7Days.add(dayData);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalConvertedDay", totalConvertedDay);
        response.put("totalNSConvertDay", totalNSConvertDay);
        response.put("totalFailedDay", totalFailedDay);
        response.put("totalConvertedWeek", totalConvertedWeek);
        response.put("totalNSConvertWeek", totalNSConvertWeek);
        response.put("totalFailedWeek", totalFailedWeek);
        response.put("weeklyData", last7Days);

        return ResponseEntity.ok(response);
    }
}
