package com.example.workflowengine.dto;

public class KullaniciTalepResponse {

    private Long surecId;
    private String surecDurum;

    private Long taskId;
    private String taskDurum;

    private String isTuruAdi;
    private String yorum;

    public KullaniciTalepResponse(
            Long surecId,
            String surecDurum,
            Long taskId,
            String taskDurum,
            String isTuruAdi,
            String yorum
    ) {
        this.surecId = surecId;
        this.surecDurum = surecDurum;
        this.taskId = taskId;
        this.taskDurum = taskDurum;
        this.isTuruAdi = isTuruAdi;
        this.yorum = yorum;
    }

    public Long getSurecId() { return surecId; }

    public String getSurecDurum() { return surecDurum; }

    public Long getTaskId() { return taskId; }

    public String getTaskDurum() { return taskDurum; }

    public String getIsTuruAdi() { return isTuruAdi; }

    public String getYorum() { return yorum; }
}