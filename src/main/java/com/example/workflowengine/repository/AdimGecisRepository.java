package com.example.workflowengine.repository;

import com.example.workflowengine.entity.AdimGecis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdimGecisRepository extends JpaRepository<AdimGecis, Long> {

    AdimGecis findFirstByKaynakAdimId(Long kaynakAdimId);

}