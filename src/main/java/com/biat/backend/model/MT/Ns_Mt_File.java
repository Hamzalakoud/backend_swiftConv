package com.biat.backend.model.MT;

import jakarta.persistence.*;

import java.sql.Clob;
import java.time.LocalDateTime;

@Entity
@Table(name="NS_MT_FILE")
public class Ns_Mt_File {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(name = "detect_id")
private Long detectId;
private String filename;
private String msgCateg;
private String msgType;
private String bicEm;
private String bicDe;
private String uetr;
private double amount;
private String currency;
private String customerAccount;
private String tag71A;
private String status;
@Lob
private Clob msgOrig;
private LocalDateTime createdAt;

private LocalDateTime updatedAt;
}