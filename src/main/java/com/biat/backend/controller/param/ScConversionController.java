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

    // Filtered GET with query params
    @GetMapping("/filtered")
    public ResponseEntity<List<ScConversion>> getFiltered(@RequestParam Map<String, String> params) {
        List<ScConversion> all = repository.findAll();

        List<ScConversion> filtered = all.stream()
            .filter(sc -> filterMatch(sc.getFilename(), params.get("filename")))
            .filter(sc -> filterMatch(sc.getMsgCateg(), params.get("msgCateg")))
            .filter(sc -> filterMatch(sc.getMsgType(), params.get("msgType")))
            .filter(sc -> filterMatch(sc.getBicEm(), params.get("bicEm")))
            .filter(sc -> filterMatch(sc.getBicDe(), params.get("bicDe")))
            .filter(sc -> filterMatch(sc.getUertr(), params.get("uertr")))
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
            .filter(sc -> filterMatch(sc.getCurrency(), params.get("currency")))
            .filter(sc -> filterMatch(sc.getCustomerAccount(), params.get("customerAccount")))
            .filter(sc -> filterMatch(sc.getTag71A(), params.get("tag71A")))
            .filter(sc -> filterMatch(sc.getStatus(), params.get("status")))
            .filter(sc -> filterMatch(sc.getSens(), params.get("sens")))
            .filter(sc -> filterMatch(sc.getToConvert(), params.get("toConvert")))
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

    // Other field-specific GETs omitted here for brevity (keep as in your code)...

    @GetMapping("/count")
    public ResponseEntity<Long> countMessages() {
        return ResponseEntity.ok(repository.count());
    }

    // === New dashboard stats endpoint ===
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        LocalDate today = LocalDate.now();
        // Assuming creationDate is LocalDateTime; adjust if needed
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
