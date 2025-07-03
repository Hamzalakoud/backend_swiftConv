package com.biat.backend.repository.MX;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biat.backend.model.MX.ScMxFile;

import java.util.List;

public interface ScMxFileRepository extends JpaRepository<ScMxFile, Long> {

    List<ScMxFile> findByCurrency(String currency);

    List<ScMxFile> findByCustomerAccount(String customerAccount);

    List<ScMxFile> findByFilename(String filename);

    List<ScMxFile> findByMsgCateg(String msgCateg);

    List<ScMxFile> findByMsgType(String msgType);

    List<ScMxFile> findByTag71A(String tag71A);
}
