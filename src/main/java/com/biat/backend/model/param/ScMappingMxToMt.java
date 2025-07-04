package com.biat.backend.model.param;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SC_MAPPING")
public class ScMappingMxToMt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TAG")
    private String tag;

    @Column(name = "FUNCTION")
    private String function;

    @Column(name = "PATH")
    private String path;

    @Column(name = "ATTRIBUT")
    private String attribut;

    @Column(name = "MSG_TYPE")
    private String msgType;

    @Column(name = "SENS")
    private String sens;

    @Column(name = "ORDRE")
    private String ordre;
}
