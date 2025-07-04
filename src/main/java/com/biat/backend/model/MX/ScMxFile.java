package com.biat.backend.model.MX;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Clob;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Entity
@Table(name = "SC_MX_FILE")
@Data
public class ScMxFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "detect_id")
    private Long detectFile;

    private String filename;
    private String msgCateg;
    private String msgType;
    private String bicEm;
    private String bicDe;
    private String uertr;
    private double amount;
    private String currency;
    private String customerAccount;
    private String tag71A;

    @Lob
    @JsonIgnore  // Prevent raw Clob from being serialized
    private Clob msgOrig;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Transient getter to expose Clob content as String in JSON
    @Transient
    @JsonProperty("msgOrig")
    public String getMsgOrigString() {
        if (msgOrig == null) return null;
        try {
            return msgOrig.getSubString(1, (int) msgOrig.length());
        } catch (SQLException e) {
            return "Error reading msgOrig";
        }
    }
}
