package com.biat.backend.model.MT;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SC_NS_MT_DETAILS")
@Data
public class ScNsMtDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SC_NS_MT")
    private Long scNsMt; // FK to SC_NS_MT.id

    private String tag;

    @Column(name = "value")
    private String value;

    private Integer ordre;
}
