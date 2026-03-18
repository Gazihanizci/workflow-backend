package com.example.workflowengine.repository;

import com.example.workflowengine.dto.GelenTalepResponse;
import com.example.workflowengine.dto.KullaniciTalepResponse;
import com.example.workflowengine.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Kullanıcıya atanmış ve bekleyen taskler
    List<Task> findByAtananKullaniciIdAndDurum(Long kullaniciId, String durum);

    // Belirli adım + süreç + durum
    List<Task> findBySurecIdAndAdimIdAndDurum(Long surecId, Long adimId, String durum);


    // 🔥 KULLANICININ TALEPLERİ (SON DURUM + AÇIKLAMA)
    @Query("""
    SELECT new com.example.workflowengine.dto.KullaniciTalepResponse(
        s.surecId,
        s.durum,
        t.taskId,
        t.durum,
        it.isTuruAdi,
        go.yorum,
        s.aciklama
    )
    FROM Surec s
    JOIN Task t ON t.surecId = s.surecId
    JOIN IsTuru it ON it.isTuruId = s.isTuruId
    LEFT JOIN GorevOnay go ON go.gorevId = t.taskId AND go.durum = 'RED'
    WHERE s.baslatanKullaniciId = :kullaniciId
    AND t.taskId = (
        SELECT MAX(t2.taskId)
        FROM Task t2
        WHERE t2.surecId = s.surecId
    )
    """)
    List<KullaniciTalepResponse> kullaniciTalepleri(Long kullaniciId);


    // 🔥 GELEN TALEPLER (kimden + birim bilgisi ile)
    @Query("""
SELECT new com.example.workflowengine.dto.GelenTalepResponse(
    t.taskId,
    s.surecId,
    it.isTuruAdi,
    t.durum,
    k.adSoyad,
    k.birim.birimAdi,
    s.aciklama
)
FROM Task t
JOIN Surec s ON s.surecId = t.surecId
JOIN IsTuru it ON it.isTuruId = s.isTuruId
JOIN Kullanici k ON k.id = s.baslatanKullaniciId
WHERE t.atananKullaniciId = :kullaniciId
AND t.durum = 'BEKLIYOR'
""")
    List<GelenTalepResponse> findGelenTalepler(Long kullaniciId);
}