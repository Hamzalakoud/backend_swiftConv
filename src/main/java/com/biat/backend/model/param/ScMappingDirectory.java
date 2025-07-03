package com.biat.backend.model.param;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="Sc_Mapping_Directory")
@Getter
@Setter
public class ScMappingDirectory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="REP_IN")
    private String repIn;


    @Column(name="REP_OUT")
    private String repOut;
}