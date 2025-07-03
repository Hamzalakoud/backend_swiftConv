package com.biat.backend.repository.param;

import com.biat.backend.model.param.ScConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScConversionRepository extends JpaRepository<ScConversion, Long> {

    List<ScConversion> findByCurrency(String currency);

    List<ScConversion> findByCustomerAccount(String customerAccount);

    List<ScConversion> findByFilename(String filename);

    List<ScConversion> findByMsgCateg(String msgCateg);

    List<ScConversion> findByMsgType(String msgType);

    List<ScConversion> findByTag71A(String tag71A);

    List<ScConversion> findByStatus(String status);

    List<ScConversion> findBySens(String sens);

    List<ScConversion> findByToConvert(String toConvert);

    List<ScConversion> findByBicEm(String bicEm);

    List<ScConversion> findByBicDe(String bicDe);
}
