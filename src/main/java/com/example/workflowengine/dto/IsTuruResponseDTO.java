package com.example.workflowengine.dto;

public class IsTuruResponseDTO {

    private Long isTuruId;
    private String isTuruAdi;
    private Long kategoriId;
    private String kategoriAdi;

    public IsTuruResponseDTO(Long isTuruId, String isTuruAdi, Long kategoriId, String kategoriAdi) {
        this.isTuruId = isTuruId;
        this.isTuruAdi = isTuruAdi;
        this.kategoriId = kategoriId;
        this.kategoriAdi = kategoriAdi;
    }

    public Long getIsTuruId() {
        return isTuruId;
    }

    public String getIsTuruAdi() {
        return isTuruAdi;
    }

    public Long getKategoriId() {
        return kategoriId;
    }

    public String getKategoriAdi() {
        return kategoriAdi;
    }
}