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
    @Column(name = "file_id")
    private Long scMt; // FK to SC_MT.id

    @Column(name = "element_name")
    private String tag;

    @Column(name = "element_value")
    private String value;

    @Column(name = "order_tag")
    private Integer ordre;
}
