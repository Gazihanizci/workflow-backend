package com.example.workflowengine.repository;

import com.example.workflowengine.entity.IsAkisiAdim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsAkisiAdimRepository extends JpaRepository<IsAkisiAdim, Long> {

    IsAkisiAdim findFirstByIsAkisiIdOrderByAdimSirasiAsc(Long isAkisiId);

    IsAkisiAdim findFirstByIsAkisiIdAndAdimSirasi(Long isAkisiId, Integer adimSirasi);

}