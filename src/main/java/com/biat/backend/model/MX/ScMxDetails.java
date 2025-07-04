package com.biat.backend.model.MX;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SC_MX_DETAILS")
@Data
public class ScMxDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_id")
    private Long scMxId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "element_name")
    private String elementName;

    @Column(name = "element_value")
    private String elementValue;

    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(name = "depth")
    private Integer profondeur;
}
