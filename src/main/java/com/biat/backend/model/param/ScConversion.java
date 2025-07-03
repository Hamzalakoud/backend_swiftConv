package com.biat.backend.model.param;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Clob;
import java.sql.SQLException;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SC_CONVERSION")
@Data
public class ScConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long detectId;

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
    @JsonIgnore
    @Column(name = "MSG_ORIG_MT")  // 👈 Ensures column name matches database
    private Clob msgOrigMT;

    @Lob
    @JsonIgnore
    @Column(name = "MSG_ORIG_MX")  // 👈 Ensures column name matches database
    private Clob msgOrigMX;

    private String status; // FAILED/CONVERTED/NC
    private String sens;   // E or R
    private String toConvert; // YES or NO

    private LocalDate creationDate;
    private LocalDate updateDate;

    @JsonGetter("msgOrigMT")
    public String getMsgOrigMTString() {
        try {
            if (msgOrigMT == null) {
                return null;
            }
            return msgOrigMT.getSubString(1, (int) msgOrigMT.length());
        } catch (SQLException e) {
            return "Error reading msgOrigMT";
        }
    }

    @JsonGetter("msgOrigMX")
    public String getMsgOrigMXString() {
        try {
            if (msgOrigMX == null) {
                return null;
            }
            return msgOrigMX.getSubString(1, (int) msgOrigMX.length());
        } catch (SQLException e) {
            return "Error reading msgOrigMX";
        }
    }
}
