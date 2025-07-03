package com.biat.backend.repository;

import com.biat.backend.model.ScMappingMxToMt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScMappingMxToMtRepository extends JpaRepository<ScMappingMxToMt, Long> {
}
