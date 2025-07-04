package com.biat.backend.model.MT;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "NS_MT_DETAILS")
@Data
public class ScNsMtDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_id")
    private Long scNsMt; // FK to SC_NS_MT.id

    @Column(name = "element_name")
    private String tag;

    @Column(name = "element_value")
    private String value;

    @Column(name = "order_Tag")
    private Integer ordre;
}
