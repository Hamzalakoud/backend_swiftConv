package com.biat.backend.model.MT;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "SC_NS_MT")
@Data
public class ScNsMt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String tag20;
    private String tag21;
    private String uetr;
    private String msgType;

    @Lob
    private String mtMsg;

    private String status;
    private String sens;
    private String converted;

    private LocalDate creationDate;
    private LocalDate updateDate;
}
