package com.biat.backend.repository.param;

import com.biat.backend.model.param.ScConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScConversionRepository extends JpaRepository<ScConversion, Long>, JpaSpecificationExecutor<ScConversion> {

    // Find all conversions for a specific currency
    List<ScConversion> findByCurrency(String currency);

    // Find all conversions for a specific customer account
    List<ScConversion> findByCustomerAccount(String customerAccount);

    // Find all conversions for a specific filename
    List<ScConversion> findByFilename(String filename);

    // Find all conversions by message category
    List<ScConversion> findByMsgCateg(String msgCateg);

    // Find all conversions by message type
    List<ScConversion> findByMsgType(String msgType);

    // Find all conversions for a specific tag71A
    List<ScConversion> findByTag71A(String tag71A);

    // Find all conversions by status
    List<ScConversion> findByStatus(String status);

    // Find all conversions by sens
    List<ScConversion> findBySens(String sens);

    // Find all conversions by toConvert
    List<ScConversion> findByToConvert(String toConvert);

    // Find all conversions for a specific bicEm
    List<ScConversion> findByBicEm(String bicEm);

    // Find all conversions for a specific bicDe
    List<ScConversion> findByBicDe(String bicDe);

    // Count total conversions by status and exact creation date
    @Query("SELECT COUNT(sc) FROM ScConversion sc WHERE sc.status = :status AND (sc.creationDate = :creationDate OR sc.creationDate IS NULL)")
    long countByStatusAndCreationDate(@Param("status") String status, @Param("creationDate") LocalDate creationDate);

    // Count total conversions by status and a range of creation dates (week)
    @Query("SELECT COUNT(sc) FROM ScConversion sc WHERE sc.status = :status AND (sc.creationDate BETWEEN :startDate AND :endDate OR sc.creationDate IS NULL)")
    long countByStatusAndCreationDateBetween(@Param("status") String status, 
                                              @Param("startDate") LocalDate startDate, 
                                              @Param("endDate") LocalDate endDate);

    // Get a breakdown of the data by date for the current week, handling null creationDate as well
    @Query("SELECT sc.creationDate, " +
           "SUM(CASE WHEN sc.status = 'CONVERTED' THEN 1 ELSE 0 END) AS converted, " +
           "SUM(CASE WHEN sc.status = 'NC' THEN 1 ELSE 0 END) AS ns, " +
           "SUM(CASE WHEN sc.status = 'FAILED' THEN 1 ELSE 0 END) AS failed " +
           "FROM ScConversion sc " +
           "WHERE (sc.creationDate BETWEEN :startOfWeek AND :endOfWeek OR sc.creationDate IS NULL) " +
           "GROUP BY sc.creationDate " +
           "ORDER BY sc.creationDate ASC")
    List<Object[]> findWeeklyData(@Param("startOfWeek") LocalDate startOfWeek,
                                  @Param("endOfWeek") LocalDate endOfWeek);
}
