package com.biat.backend.repository.param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biat.backend.model.param.ScMappingMtToMx;

@Repository
public interface ScMappingMtToMxRepository extends JpaRepository<ScMappingMtToMx, Long> {
}
