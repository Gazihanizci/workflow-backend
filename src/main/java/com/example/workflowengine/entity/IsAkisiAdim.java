package com.example.workflowengine.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "is_akisi_adimlari")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IsAkisiAdim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adim_id")
    private Long adimId;

    @Column(name = "is_akisi_id")
    private Long isAkisiId;

    @Column(name = "adim_sirasi")
    private Integer adimSirasi;

    @Column(name = "adim_adi")
    private String adimAdi;

    @Column(name = "onay_tipi")
    private String onayTipi;

    @Column(name = "birim_id")
    private Long birimId;

    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "gerekli_onay_sayisi")
    private Integer gerekliOnaySayisi;

    @Column(name = "sla_saat")
    private Integer slaSaat;
}