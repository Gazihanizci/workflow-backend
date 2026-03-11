package com.example.workflowengine.service;

import com.example.workflowengine.entity.*;
import com.example.workflowengine.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkflowService {

    private final SurecRepository surecRepository;
    private final TaskRepository taskRepository;
    private final KullaniciRepository kullaniciRepository;
    private final IsAkisiAdimRepository adimRepository;

    public WorkflowService(
            SurecRepository surecRepository,
            TaskRepository taskRepository,
            KullaniciRepository kullaniciRepository,
            IsAkisiAdimRepository adimRepository
    ) {
        this.surecRepository = surecRepository;
        this.taskRepository = taskRepository;
        this.kullaniciRepository = kullaniciRepository;
        this.adimRepository = adimRepository;
    }

    // süreç başlat
    public Surec baslat(Long kullaniciId, Long isTuruId) {

        Surec surec = new Surec();
        surec.setIsTuruId(isTuruId);
        surec.setBaslatanKullaniciId(kullaniciId);
        surec.setDurum("DEVAM");
        surec.setOlusturmaTarihi(LocalDateTime.now());

        surecRepository.save(surec);

        // is akışı id
        Long isAkisiId = 7L;

        // ilk adım
        IsAkisiAdim ilkAdim =
                adimRepository.findFirstByIsAkisiIdOrderByAdimSirasiAsc(isAkisiId);

        taskOlustur(surec, ilkAdim);

        return surec;
    }

    // task oluştur
    private void taskOlustur(Surec surec, IsAkisiAdim adim) {

        Kullanici baslatan =
                kullaniciRepository
                        .findById(surec.getBaslatanKullaniciId())
                        .orElseThrow();

        Long hedefKullaniciId;

        if(adim.getAdimSirasi() == 1){

            // ilk onay → talebi açanın yöneticisi
            hedefKullaniciId = baslatan.getYoneticiId();

        } else {

            // sonraki onay → bir üst yönetici
            Kullanici yonetici =
                    kullaniciRepository
                            .findById(baslatan.getYoneticiId())
                            .orElseThrow();

            hedefKullaniciId = yonetici.getYoneticiId();
        }

        Task task = new Task();

        task.setSurecId(surec.getSurecId());
        task.setAdimId(adim.getAdimId());
        task.setAtananKullaniciId(hedefKullaniciId);
        task.setDurum("BEKLIYOR");
        task.setOlusturmaTarihi(LocalDateTime.now());

        taskRepository.save(task);
    }

    // gelen talepler
    public List<Task> gelenTalepler(Long kullaniciId) {

        return taskRepository
                .findByAtananKullaniciIdAndDurum(
                        kullaniciId,
                        "BEKLIYOR"
                );
    }

    // onay
    public void onayla(Long taskId) {

        Task task = taskRepository.findById(taskId).orElseThrow();

        task.setDurum("ONAYLANDI");
        taskRepository.save(task);

        IsAkisiAdim mevcutAdim =
                adimRepository.findById(task.getAdimId()).orElseThrow();

        // sonraki adım
        IsAkisiAdim sonrakiAdim =
                adimRepository.findFirstByIsAkisiIdAndAdimSirasi(
                        mevcutAdim.getIsAkisiId(),
                        mevcutAdim.getAdimSirasi() + 1
                );

        if (sonrakiAdim == null) {

            Surec surec =
                    surecRepository.findById(task.getSurecId()).orElseThrow();

            surec.setDurum("TAMAMLANDI");

            surecRepository.save(surec);

            return;
        }

        Surec surec =
                surecRepository.findById(task.getSurecId()).orElseThrow();

        taskOlustur(surec, sonrakiAdim);
    }
}