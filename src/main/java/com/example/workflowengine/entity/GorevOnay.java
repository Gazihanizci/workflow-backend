package com.example.workflowengine.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "gorev_onaylari")
@Getter
@Setter
public class GorevOnay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long onayId;

    @Column(name = "gorev_id")
    private Long gorevId;

    @Column(name = "kullanici_id")
    private Long kullaniciId;

    private String durum;

    private String yorum;

    private LocalDateTime tarih;
}