package com.example.workflowengine.dto;

public class GelenTalepResponse {

    private Long taskId;
    private Long surecId;
    private String isTuruAdi;
    private String durum;

    public GelenTalepResponse(Long taskId, Long surecId, String isTuruAdi, String durum) {
        this.taskId = taskId;
        this.surecId = surecId;
        this.isTuruAdi = isTuruAdi;
        this.durum = durum;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getSurecId() {
        return surecId;
    }

    public String getIsTuruAdi() {
        return isTuruAdi;
    }

    public String getDurum() {
        return durum;
    }
}