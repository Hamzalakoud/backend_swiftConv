package com.biat.backend.service.param;

import com.biat.backend.dto.DashboardStatsDTO;
import com.biat.backend.model.param.ScConversion;
import com.biat.backend.repository.param.ScConversionRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScConversionService {

    private final ScConversionRepository repository;

    // Fetch a conversion by its ID
    public ScConversion getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversion record not found with id: " + id));
    }

    // Fetch all conversions
    public List<ScConversion> getAll() {
        return repository.findAll();
    }

    // Count the total number of messages
    public long countMessages() {
        return repository.count();
    }

    // Fetch daily and weekly stats
    public DashboardStatsDTO getDashboardStats() {
        // Get today's date and the start of the current week
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1); // Monday of current week

        // 1. Daily statistics - Handle null values in creationDate
        long totalConvertedDay = repository.countByStatusAndCreationDate("CONVERTED", today);
        long totalNSConvertDay = repository.countByStatusAndCreationDate("NC", today);
        long totalFailedDay = repository.countByStatusAndCreationDate("FAILED", today);

        // 2. Weekly statistics - Handle null values in creationDate
        long totalConvertedWeek = repository.countByStatusAndCreationDateBetween("CONVERTED", startOfWeek, today);
        long totalNSConvertWeek = repository.countByStatusAndCreationDateBetween("NC", startOfWeek, today);
        long totalFailedWeek = repository.countByStatusAndCreationDateBetween("FAILED", startOfWeek, today);

        // 3. Weekly breakdown data (for each day in the current week) - Handle null values in creationDate
        List<Object[]> weeklyData = repository.findWeeklyData(startOfWeek, today);

        // Prepare the result DTO
        DashboardStatsDTO result = new DashboardStatsDTO();
        result.setTotalConvertedDay(totalConvertedDay);
        result.setTotalNSConvertDay(totalNSConvertDay);
        result.setTotalFailedDay(totalFailedDay);
        result.setTotalConvertedWeek(totalConvertedWeek);
        result.setTotalNSConvertWeek(totalNSConvertWeek);
        result.setTotalFailedWeek(totalFailedWeek);

        // Convert weekly data into the desired format
        List<DashboardStatsDTO.WeeklyData> weeklyDataList = weeklyData.stream()
                .map(row -> {
                    DashboardStatsDTO.WeeklyData data = new DashboardStatsDTO.WeeklyData();
                    data.setDate(row[0].toString()); // Date as string (e.g., "2025-07-01")
                    data.setConverted((Long) row[1]);
                    data.setNs((Long) row[2]);
                    data.setFailed((Long) row[3]);
                    return data;
                })
                .collect(Collectors.toList());

        result.setWeeklyData(weeklyDataList);

        return result;
    }

    // Filtering method for conversions based on various parameters
    public List<ScConversion> getFilteredFiles(
            String filename,
            String msgCateg,
            String msgType,
            String bicEm,
            String bicDe,
            String uertr,
            Double amount,
            String currency,
            String customerAccount,
            String tag71A,
            String status,
            String sens,
            String toConvert,
            String creationDate
    ) {
        System.out.println("Filtering by filename: " + filename);

        Specification<ScConversion> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Apply filename filter if provided
            if (filename != null && !filename.isEmpty()) {
                String baseName = filename.toLowerCase().split("\\.")[0];
                System.out.println("Base filename extracted: " + baseName);
                Predicate startsWithBase = builder.like(builder.lower(root.get("filename")), baseName + ".%");
                Predicate equalsBase = builder.equal(builder.lower(root.get("filename")), baseName);
                predicates.add(builder.or(startsWithBase, equalsBase));
            }

            // Apply other filters
            if (msgCateg != null && !msgCateg.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("msgCateg")), "%" + msgCateg.toLowerCase() + "%"));

            if (msgType != null && !msgType.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("msgType")), "%" + msgType.toLowerCase() + "%"));

            if (bicEm != null && !bicEm.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("bicEm")), "%" + bicEm.toLowerCase() + "%"));

            if (bicDe != null && !bicDe.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("bicDe")), "%" + bicDe.toLowerCase() + "%"));

            if (uertr != null && !uertr.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("uertr")), "%" + uertr.toLowerCase() + "%"));

            if (amount != null)
                predicates.add(builder.equal(root.get("amount"), amount));

            if (currency != null && !currency.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("currency")), "%" + currency.toLowerCase() + "%"));

            if (customerAccount != null && !customerAccount.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("customerAccount")), "%" + customerAccount.toLowerCase() + "%"));

            if (tag71A != null && !tag71A.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("tag71A")), "%" + tag71A.toLowerCase() + "%"));

            if (status != null && !status.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("status")), "%" + status.toLowerCase() + "%"));

            if (sens != null && !sens.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("sens")), "%" + sens.toLowerCase() + "%"));

            if (toConvert != null && !toConvert.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("toConvert")), "%" + toConvert.toLowerCase() + "%"));

            // Handle creationDate, ensuring it is not null
            if (creationDate != null && !creationDate.isEmpty()) {
                try {
                    LocalDate date = LocalDate.parse(creationDate);
                    predicates.add(builder.equal(root.get("creationDate"), date));
                } catch (Exception e) {
                    // Invalid date format, just ignore the filter and don't add it to the query
                    System.out.println("Invalid creationDate format: " + creationDate);
                }
            } else {
                // If creationDate is null or empty, filter out records with null creationDate
                predicates.add(builder.isNotNull(root.get("creationDate")));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(spec);
    }
}
