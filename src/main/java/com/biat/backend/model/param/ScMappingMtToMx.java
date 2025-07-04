package com.biat.backend.model.param;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SC_MAPPING_MT_TO_MX")
public class ScMappingMtToMx {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "mt_type")
private String mtType;

@Column(name = "mt_tag")
private String mtTag;

@Column(name = "mt_attribut")
private String attribut;

@Column(name = "mx_type")
private String mxType;

@Column(name = "mx_path")
private String mxPath;

@Column(name = "conv_func")
private String convFunc;

@Column(name = "niveau")
private String niveau;

}