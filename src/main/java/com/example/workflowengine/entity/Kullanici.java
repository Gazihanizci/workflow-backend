package com.example.workflowengine.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kullanicilar")
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kullanici_id")
    private Long id;

    @Column(name = "ad_soyad", nullable = false)
    private String adSoyad;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "yonetici_id")
    private Long yoneticiId;

    // rol_id sadece okunur olacak
    @Column(name = "rol_id", insertable = false, updatable = false)
    private Long rolId;

    @Column(name = "parola_hash", nullable = false)
    private String parolaHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "birim_id")
    private Birim birim;

    // ========================
    // GETTER / SETTER
    // ========================

    public Long getId() {
        return id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getYoneticiId() {
        return yoneticiId;
    }

    public void setYoneticiId(Long yoneticiId) {
        this.yoneticiId = yoneticiId;
    }

    public Long getRolId() {
        return rolId;
    }

    public String getParolaHash() {
        return parolaHash;
    }

    public void setParolaHash(String parolaHash) {
        this.parolaHash = parolaHash;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Birim getBirim() {
        return birim;
    }

    public void setBirim(Birim birim) {
        this.birim = birim;
    }
}