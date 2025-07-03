package com.biat.backend.model.param;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SC_PARAM_GLOBAL")
@Data
public class ScParamGlobal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "element_name")
    private String element;  // répertoire IN/OUT

    @Column(name = "element_value")
    private String valeur;   // Path du répertoire
}
