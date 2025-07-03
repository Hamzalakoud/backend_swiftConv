package com.biat.backend.service.param;

import com.biat.backend.model.param.ScConversion;
import com.biat.backend.repository.param.ScConversionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScConversionService {

    private final ScConversionRepository repository;

    public ScConversion getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversion record not found with id: " + id));
    }

    public List<ScConversion> getByFilename(String filename) {
        List<ScConversion> files = repository.findByFilename(filename);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with filename: " + filename);
        }
        return files;
    }

    public List<ScConversion> getByMsgCateg(String msgCateg) {
        List<ScConversion> files = repository.findByMsgCateg(msgCateg);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with msgCateg: " + msgCateg);
        }
        return files;
    }

    public List<ScConversion> getByMsgType(String msgType) {
        List<ScConversion> files = repository.findByMsgType(msgType);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with msgType: " + msgType);
        }
        return files;
    }

    public List<ScConversion> getByBicEm(String bicEm) {
        List<ScConversion> files = repository.findByBicEm(bicEm);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with BIC Em: " + bicEm);
        }
        return files;
    }

    public List<ScConversion> getByBicDe(String bicDe) {
        List<ScConversion> files = repository.findByBicDe(bicDe);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with BIC De: " + bicDe);
        }
        return files;
    }

    public List<ScConversion> getByCurrency(String currency) {
        List<ScConversion> files = repository.findByCurrency(currency);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with currency: " + currency);
        }
        return files;
    }

    public List<ScConversion> getByCustomerAccount(String account) {
        List<ScConversion> files = repository.findByCustomerAccount(account);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with account: " + account);
        }
        return files;
    }

    public List<ScConversion> getByTag71A(String tag71A) {
        List<ScConversion> files = repository.findByTag71A(tag71A);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with tag71A: " + tag71A);
        }
        return files;
    }

    public List<ScConversion> getByStatus(String status) {
        List<ScConversion> files = repository.findByStatus(status);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with status: " + status);
        }
        return files;
    }

    public List<ScConversion> getBySens(String sens) {
        List<ScConversion> files = repository.findBySens(sens);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with sens: " + sens);
        }
        return files;
    }

    public List<ScConversion> getByToConvert(String toConvert) {
        List<ScConversion> files = repository.findByToConvert(toConvert);
        if (files.isEmpty()) {
            throw new RuntimeException("No records found with toConvert: " + toConvert);
        }
        return files;
    }

    public List<ScConversion> getAll() {
        return repository.findAll();
    }

    public long countMessages() {
    return repository.count();
}

}
