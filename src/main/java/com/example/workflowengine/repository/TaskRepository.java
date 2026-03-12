package com.example.workflowengine.repository;

import com.example.workflowengine.dto.KullaniciTalepResponse;
import com.example.workflowengine.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAtananKullaniciIdAndDurum(Long kullaniciId, String durum);
    List<Task> findBySurecIdAndAdimIdAndDurum(Long surecId, Long adimId, String durum);
    @Query("""
SELECT new com.example.workflowengine.dto.KullaniciTalepResponse(
    s.surecId,
    s.durum,
    t.taskId,
    t.durum,
    go.yorum
)
FROM Surec s
JOIN Task t ON t.surecId = s.surecId
LEFT JOIN GorevOnay go ON go.gorevId = t.taskId AND go.durum = 'RED'
WHERE s.baslatanKullaniciId = :kullaniciId
""")
    List<KullaniciTalepResponse> kullaniciTalepleri(Long kullaniciId);
}