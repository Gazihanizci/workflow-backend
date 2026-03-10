package com.example.workflowengine.repository;

import com.example.workflowengine.dto.KullaniciResponseDTO;
import com.example.workflowengine.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {

    Optional<Kullanici> findByEmail(String email);

    @Query("""
        select new com.example.workflowengine.dto.KullaniciResponseDTO(
            k.id,
            k.adSoyad,
            k.email,
            k.rol.rolAdi,
            k.birim.birimAdi
        )
        from Kullanici k
        where k.email = :email
    """)
    Optional<KullaniciResponseDTO> kullaniciBilgisi(String email);

}