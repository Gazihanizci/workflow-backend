package com.example.workflowengine.dto;

public class KullaniciResponseDTO {

    private Long id;
    private String adSoyad;
    private String email;
    private String rol;
    private String birim;

    public KullaniciResponseDTO(Long id, String adSoyad, String email, String rol, String birim) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.email = email;
        this.rol = rol;
        this.birim = birim;
    }

    public Long getId() {
        return id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public String getBirim() {
        return birim;
    }
}