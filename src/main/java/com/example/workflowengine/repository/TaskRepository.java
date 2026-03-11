package com.example.workflowengine.repository;

import com.example.workflowengine.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAtananKullaniciIdAndDurum(Long kullaniciId, String durum);
    List<Task> findBySurecIdAndAdimIdAndDurum(Long surecId, Long adimId, String durum);
}