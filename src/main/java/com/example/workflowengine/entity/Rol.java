package com.example.workflowengine.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roller")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "rol_adi", nullable = false)
    private String rolAdi;

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolAdi() {
        return rolAdi;
    }

    public void setRolAdi(String rolAdi) {
        this.rolAdi = rolAdi;
    }
}