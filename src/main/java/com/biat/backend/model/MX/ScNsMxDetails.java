package com.biat.backend.model.MX;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "SC_NS_MX_DETAILS")
@Data
public class ScNsMxDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SC_MX_id")
    private Long scMxId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "element_name")
    private String elementName;

    @Column(name = "element_value")
    private String elementValue;

    @Column(name = "order_index")
    private Integer orderIndex;

    private Integer profondeur;

    private LocalDate creationDate;

    private LocalDate updateDate;
}
