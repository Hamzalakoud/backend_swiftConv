package com.biat.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "BIAT_USER_SEQ", allocationSize = 1)
    private Long id;

    private String firstname;

    private String lastname;

    private String password;

    private String role;

    private String email;

    private Boolean status;

    @Column(name = "creationdate")
    private LocalDateTime creationDate;

    @Column(name = "lastupdatedate")
    private LocalDateTime lastUpdateDate;
}
