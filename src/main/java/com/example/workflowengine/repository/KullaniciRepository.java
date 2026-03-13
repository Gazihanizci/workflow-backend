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
            r.rolAdi,
            b.birimAdi
        )
        from Kullanici k
        left join k.rol r
        left join k.birim b
        where k.id = :id
    """)
    Optional<KullaniciResponseDTO> kullaniciBilgisiById(Long id);

    Optional<Kullanici> findFirstByRolId(Long rolId);

    // 🔹 Birim bazlı rol arama (nested property)
    Optional<Kullanici> findFirstByRolIdAndBirim_BirimId(Long rolId, Long birimId);
}