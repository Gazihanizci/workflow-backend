package com.example.workflowengine.repository;

import com.example.workflowengine.dto.IsTuruResponseDTO;
import com.example.workflowengine.entity.IsTuru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IsTuruRepository extends JpaRepository<IsTuru, Long> {

    @Query("""
        select new com.example.workflowengine.dto.IsTuruResponseDTO(
            i.isTuruId,
            i.isTuruAdi,
            i.kategori.kategoriId,
            i.kategori.ad
        )
        from IsTuru i
        where i.kategori.kategoriId = :kategoriId
        order by i.isTuruAdi
    """)
    List<IsTuruResponseDTO> findAllByKategoriId(Long kategoriId);
}