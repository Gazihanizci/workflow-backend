package com.example.workflowengine.dto;

public class IsAkisiResponse {

    private Long isTuruId;
    private String isAkisiAdi;

    public IsAkisiResponse(Long isTuruId, String isAkisiAdi) {
        this.isTuruId = isTuruId;
        this.isAkisiAdi = isAkisiAdi;
    }

    public Long getIsTuruId() {
        return isTuruId;
    }

    public String getIsAkisiAdi() {
        return isAkisiAdi;
    }
}