package com.biat.backend.repository;

import com.biat.backend.model.ScMappingMtToMx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScMappingMtToMxRepository extends JpaRepository<ScMappingMtToMx, Long> {
}
