package com.example.workflowengine.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "birimler")
public class Birim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birim_id")
    private Long birimId;

    @Column(name = "birim_adi", nullable = false)
    private String birimAdi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategori_id")
    private Kategori kategori;

    public Long getBirimId() {
        return birimId;
    }

    public void setBirimId(Long birimId) {
        this.birimId = birimId;
    }

    public String getBirimAdi() {
        return birimAdi;
    }

    public void setBirimAdi(String birimAdi) {
        this.birimAdi = birimAdi;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
}