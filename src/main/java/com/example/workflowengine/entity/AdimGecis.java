package com.example.workflowengine.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adim_gecisleri")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdimGecis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gecis_id")
    private Long gecisId;

    @Column(name = "kaynak_adim_id")
    private Long kaynakAdimId;

    @Column(name = "hedef_adim_id")
    private Long hedefAdimId;

    private String kosul;
}