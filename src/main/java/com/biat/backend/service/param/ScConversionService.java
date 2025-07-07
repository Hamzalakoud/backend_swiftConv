package com.biat.backend.service.param;

import com.biat.backend.model.param.ScConversion;
import com.biat.backend.repository.param.ScConversionRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScConversionService {

    private final ScConversionRepository repository;

    public ScConversion getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversion record not found with id: " + id));
    }

    public List<ScConversion> getAll() {
        return repository.findAll();
    }

    public long countMessages() {
        return repository.count();
    }

    public List<ScConversion> getFiltered(
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
        Specification<ScConversion> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filename != null && !filename.isEmpty())
                predicates.add(builder.like(builder.lower(root.get("filename")), "%" + filename.toLowerCase() + "%"));

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

            if (creationDate != null && !creationDate.isEmpty()) {
                try {
                    LocalDate date = LocalDate.parse(creationDate);
                    predicates.add(builder.equal(root.get("creationDate"), date));
                } catch (Exception e) {
                    // invalid date, ignore
                }
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(spec);
    }
}
