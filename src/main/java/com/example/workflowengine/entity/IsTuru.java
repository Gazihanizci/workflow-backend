package com.example.workflowengine.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "is_turleri")
public class IsTuru {

    @Id
    @Column(name = "is_turu_id")
    private Long isTuruId;

    @Column(name = "is_turu_adi")
    private String isTuruAdi;

    @ManyToOne
    @JoinColumn(name = "kategori_id")
    private Kategori kategori;

    // EKLENECEK
    @Column(name = "is_akisi_id")
    private Long isAkisiId;

    public Long getIsTuruId() {
        return isTuruId;
    }

    public String getIsTuruAdi() {
        return isTuruAdi;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public Long getIsAkisiId() {
        return isAkisiId;
    }
}