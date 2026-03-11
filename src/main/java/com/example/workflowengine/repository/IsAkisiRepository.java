package com.example.workflowengine.repository;

import com.example.workflowengine.entity.IsAkisi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsAkisiRepository extends JpaRepository<IsAkisi, Long> {

    IsAkisi findFirstByIsTuruId(Long isTuruId);

}