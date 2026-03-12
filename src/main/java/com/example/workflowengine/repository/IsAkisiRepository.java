package com.example.workflowengine.repository;

import com.example.workflowengine.dto.IsAkisiResponse;
import com.example.workflowengine.entity.IsAkisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IsAkisiRepository extends JpaRepository<IsAkisi, Long> {

    @Query("""
           SELECT new com.example.workflowengine.dto.IsAkisiResponse(
               i.isTuruId,
               i.isAkisiAdi
           )
           FROM IsAkisi i
           """)
    List<IsAkisiResponse> findAllIsAkislari();
}