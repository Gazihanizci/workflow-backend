package com.example.workflowengine.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasklar")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private Long surecId;

    private Long adimId;

    private Long atananKullaniciId;

    private String durum;

    private LocalDateTime olusturmaTarihi;

    public Long getTaskId() {
        return taskId;
    }

    public Long getSurecId() {
        return surecId;
    }

    public void setSurecId(Long surecId) {
        this.surecId = surecId;
    }

    public Long getAdimId() {
        return adimId;
    }

    public void setAdimId(Long adimId) {
        this.adimId = adimId;
    }

    public Long getAtananKullaniciId() {
        return atananKullaniciId;
    }

    public void setAtananKullaniciId(Long atananKullaniciId) {
        this.atananKullaniciId = atananKullaniciId;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public LocalDateTime getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public void setOlusturmaTarihi(LocalDateTime olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }
}