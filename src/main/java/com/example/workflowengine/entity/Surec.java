package com.example.workflowengine.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "surecler")
public class Surec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surecId;

    private Long isTuruId;

    private Long baslatanKullaniciId;

    private String durum;

    private LocalDateTime olusturmaTarihi;

    public Long getSurecId() {
        return surecId;
    }

    public Long getIsTuruId() {
        return isTuruId;
    }

    public void setIsTuruId(Long isTuruId) {
        this.isTuruId = isTuruId;
    }

    public Long getBaslatanKullaniciId() {
        return baslatanKullaniciId;
    }

    public void setBaslatanKullaniciId(Long baslatanKullaniciId) {
        this.baslatanKullaniciId = baslatanKullaniciId;
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
    @Column(name = "aciklama")
    private String aciklama;

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
    public void setOlusturmaTarihi(LocalDateTime olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }
}