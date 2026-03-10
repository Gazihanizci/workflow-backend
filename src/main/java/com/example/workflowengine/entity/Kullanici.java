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

    @Column(name = "parola_hash", nullable = false)
    private String parolaHash;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "birim_id")
    private Birim birim;

    public Long getId() {
        return id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getEmail() {
        return email;
    }

    public String getParolaHash() {
        return parolaHash;
    }

    public Rol getRol() {
        return rol;
    }

    public Birim getBirim() {
        return birim;
    }
}