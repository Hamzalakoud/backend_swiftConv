package com.biat.backend.model.MT;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SC_MT_DETAILS")
@Data
public class ScMtDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key reference to SC_MT
    @Column(name = "SC_MT")
    private Long scMt; // FK to SC_MT.id

    private String tag;

    @Column(name = "value")
    private String value;

    private Integer ordre;
}
