package com.example.workflowengine.dto;

public class GelenTalepResponse {

    private Long taskId;
    private Long surecId;
    private String isTuruAdi;
    private String durum;
    private String aciklama;
    private String baslatanAdSoyad; // ✅ yeni
    private String birimAdi;        // ✅ yeni

    public GelenTalepResponse(
            Long taskId,
            Long surecId,
            String isTuruAdi,
            String durum, String aciklama,
            String baslatanAdSoyad,
            String birimAdi
    ) {
        this.taskId = taskId;
        this.surecId = surecId;
        this.isTuruAdi = isTuruAdi;
        this.durum = durum;
        this.aciklama = aciklama;
        this.baslatanAdSoyad = baslatanAdSoyad;
        this.birimAdi = birimAdi;
    }

    public Long getTaskId() {
        return taskId;
    }
    public String getAciklama() {
        return aciklama;
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

    public String getBaslatanAdSoyad() {
        return baslatanAdSoyad;
    }

    public String getBirimAdi() {
        return birimAdi;
    }
}