package com.biat.backend.model.MX;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "SC_NS_MX")
@Data
public class ScNsMx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @Column(name = "message_type")
    private String messageType;

    @Lob
    private String mxMsg;

    private String status;

    private LocalDate creationDate;

    private LocalDate updateDate;
}
