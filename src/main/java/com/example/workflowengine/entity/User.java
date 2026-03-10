package com.example.workflowengine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "kullanicilar")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kullanici_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "parola_hash")
    private String parolaHash;
}