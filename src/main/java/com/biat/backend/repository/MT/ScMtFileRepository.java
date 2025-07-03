package com.biat.backend.repository.MT;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biat.backend.model.MT.ScMtFile;

import java.util.List;

public interface ScMtFileRepository extends JpaRepository<ScMtFile, Long> {

    List<ScMtFile> findByCurrency(String currency);

    List<ScMtFile> findByCustomerAccount(String customerAccount);

    List<ScMtFile> findByFilename(String filename);

    List<ScMtFile> findByMsgCateg(String msgCateg);

    List<ScMtFile> findByMsgType(String msgType);

    List<ScMtFile> findByTag71A(String tag71A);
}
