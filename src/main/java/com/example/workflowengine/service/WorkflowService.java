package com.example.workflowengine.service;

import com.example.workflowengine.dto.GelenTalepResponse;
import com.example.workflowengine.dto.KullaniciTalepResponse;
import com.example.workflowengine.entity.*;
import com.example.workflowengine.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkflowService {

    private final IsAkisiRepository isAkisiRepository;
    private final SurecRepository surecRepository;
    private final TaskRepository taskRepository;
    private final KullaniciRepository kullaniciRepository;
    private final IsAkisiAdimRepository adimRepository;
    private final GorevOnayRepository gorevOnayRepository;

    public WorkflowService(
            IsAkisiRepository isAkisiRepository,
            SurecRepository surecRepository,
            TaskRepository taskRepository,
            KullaniciRepository kullaniciRepository,
            IsAkisiAdimRepository adimRepository,
            GorevOnayRepository gorevOnayRepository
    ) {
        this.isAkisiRepository = isAkisiRepository;
        this.surecRepository = surecRepository;
        this.taskRepository = taskRepository;
        this.kullaniciRepository = kullaniciRepository;
        this.adimRepository = adimRepository;
        this.gorevOnayRepository = gorevOnayRepository;
    }

    // süreç başlat
    public Surec baslat(Long kullaniciId, Long isTuruId) {

        Surec surec = new Surec();
        surec.setIsTuruId(isTuruId);
        surec.setBaslatanKullaniciId(kullaniciId);
        surec.setDurum("DEVAM");
        surec.setOlusturmaTarihi(LocalDateTime.now());

        surecRepository.save(surec);

        // workflow bul
        IsAkisi isAkisi =
                isAkisiRepository
                        .findByIsTuruId(isTuruId)
                        .orElseThrow(() -> new RuntimeException("Workflow bulunamadı"));

        IsAkisiAdim ilkAdim =
                adimRepository
                        .findFirstByIsAkisiIdOrderByAdimSirasiAsc(isAkisi.getId());

        if (ilkAdim == null) {
            throw new RuntimeException("Workflow adımı yok");
        }

        taskOlustur(surec, ilkAdim);

        return surec;
    }


    // TASK OLUŞTUR
    private void taskOlustur(Surec surec, IsAkisiAdim adim) {
        Kullanici baslatan = kullaniciRepository.findById(surec.getBaslatanKullaniciId())
                .orElseThrow(() -> new RuntimeException("Başlayan kullanıcı bulunamadı"));

        Long hedefKullaniciId = null;
        Long rolId = adim.getRolId();
        Long birimId = adim.getBirimId();

        // 1. ÖNCELİK: Eğer adım "Birim Yöneticisi" ise (Senin örneğinde rol_id 2 genelde budur)
        // Ve birim_id null ise, başlatan kişinin kendi yöneticisine gitmeli.
        if (rolId == 2 && birimId == null) {
            hedefKullaniciId = baslatan.getYoneticiId();
            if (hedefKullaniciId == null) {
                throw new RuntimeException("Kullanıcının hiyerarşik yöneticisi tanımlı değil.");
            }
        }
        // 2. ÖNCELİK: Hem Birim hem Rol doluysa (Örn: IK Birimi - IK Onaycısı)
        else if (birimId != null && rolId != null) {
            hedefKullaniciId = kullaniciRepository
                    .findFirstByRolIdAndBirim_BirimId(rolId, birimId)
                    .map(Kullanici::getId)
                    .orElseThrow(() -> new RuntimeException(adim.getAdimAdi() + " için ilgili birimde kullanıcı bulunamadı."));
        }
        // 3. ÖNCELİK: Sadece Rol varsa (Örn: Genel Müdür)
        else if (rolId != null) {
            hedefKullaniciId = kullaniciRepository
                    .findFirstByRolId(rolId)
                    .map(Kullanici::getId)
                    .orElseThrow(() -> new RuntimeException(adim.getAdimAdi() + " için uygun rol sahibi bulunamadı."));
        }

        if (hedefKullaniciId == null) {
            throw new RuntimeException("Atama yapılacak kullanıcı tespit edilemedi.");
        }

        // TASK KAYDI
        Task task = new Task();
        task.setSurecId(surec.getSurecId());
        task.setAdimId(adim.getAdimId());
        task.setAtananKullaniciId(hedefKullaniciId);
        task.setDurum("BEKLIYOR");
        task.setOlusturmaTarihi(LocalDateTime.now());

        taskRepository.save(task);
    }


    // gelen talepler
    public List<GelenTalepResponse> gelenTalepler(Long kullaniciId) {
        return taskRepository.findGelenTalepler(kullaniciId);
    }


    // RED
    public void reddet(Long taskId, String yorum) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task bulunamadı"));

        task.setDurum("RED");
        taskRepository.save(task);

        Surec surec = surecRepository.findById(task.getSurecId())
                .orElseThrow(() -> new RuntimeException("Süreç bulunamadı"));

        surec.setDurum("REDDEDILDI");
        surecRepository.save(surec);

        GorevOnay onay = new GorevOnay();

        onay.setGorevId(task.getTaskId());
        onay.setKullaniciId(task.getAtananKullaniciId());
        onay.setDurum("RED");
        onay.setYorum(yorum);
        onay.setTarih(LocalDateTime.now());

        gorevOnayRepository.save(onay);
    }


    // ONAY
    public void onayla(Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task bulunamadı"));

        task.setDurum("ONAYLANDI");
        taskRepository.save(task);

        IsAkisiAdim mevcutAdim =
                adimRepository
                        .findById(task.getAdimId())
                        .orElseThrow(() -> new RuntimeException("Adım bulunamadı"));

        IsAkisiAdim sonrakiAdim =
                adimRepository
                        .findFirstByIsAkisiIdAndAdimSirasi(
                                mevcutAdim.getIsAkisiId(),
                                mevcutAdim.getAdimSirasi() + 1
                        );

        // workflow bitti
        if (sonrakiAdim == null) {

            Surec surec =
                    surecRepository
                            .findById(task.getSurecId())
                            .orElseThrow(() -> new RuntimeException("Süreç bulunamadı"));

            surec.setDurum("TAMAMLANDI");

            surecRepository.save(surec);

            return;
        }

        Surec surec =
                surecRepository
                        .findById(task.getSurecId())
                        .orElseThrow(() -> new RuntimeException("Süreç bulunamadı"));

        taskOlustur(surec, sonrakiAdim);
    }


    // kullanıcının oluşturduğu talepler
    public List<KullaniciTalepResponse> kullaniciTalepleri(Long kullaniciId) {
        return taskRepository.kullaniciTalepleri(kullaniciId);
    }
}