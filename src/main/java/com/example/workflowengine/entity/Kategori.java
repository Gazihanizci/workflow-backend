package com.example.workflowengine.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kategoriler")
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kategori_id")
    private Long kategoriId;

    @Column(name = "ad", nullable = false)
    private String ad;

    public Long getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Long kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }
}