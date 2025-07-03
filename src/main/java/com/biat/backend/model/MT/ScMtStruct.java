package com.biat.backend.model.MT;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SC_MT_STRUCT")
@Data
public class ScMtStruct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bodyTag;  // e.g., "20", "50_SeqB"

    private Integer ordre;   // display order
}
