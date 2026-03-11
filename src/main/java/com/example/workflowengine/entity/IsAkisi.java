package com.example.workflowengine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "is_akislari")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IsAkisi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "is_akisi_id")
    private Long id;

    @Column(name = "is_turu_id")
    private Long isTuruId;

    @Column(name = "is_akisi_adi")
    private String isAkisiAdi;

    private Integer versiyon;

    @Column(name = "olusturma_tarihi")
    private LocalDateTime olusturmaTarihi;
}