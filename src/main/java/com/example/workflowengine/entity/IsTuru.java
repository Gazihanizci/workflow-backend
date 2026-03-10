package com.example.workflowengine.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "is_turleri")
public class IsTuru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "is_turu_id")
    private Long isTuruId;

    @Column(name = "is_turu_adi", nullable = false)
    private String isTuruAdi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategori_id")
    private Kategori kategori;

    public Long getIsTuruId() {
        return isTuruId;
    }

    public void setIsTuruId(Long isTuruId) {
        this.isTuruId = isTuruId;
    }

    public String getIsTuruAdi() {
        return isTuruAdi;
    }

    public void setIsTuruAdi(String isTuruAdi) {
        this.isTuruAdi = isTuruAdi;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
}