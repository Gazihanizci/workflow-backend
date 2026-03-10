package com.example.workflowengine.service;

import com.example.workflowengine.dto.IsTuruResponseDTO;
import com.example.workflowengine.entity.Birim;
import com.example.workflowengine.entity.Kategori;
import com.example.workflowengine.entity.Kullanici;
import com.example.workflowengine.repository.IsTuruRepository;
import com.example.workflowengine.repository.KullaniciRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IsTuruService {

    private final IsTuruRepository isTuruRepository;
    private final KullaniciRepository kullaniciRepository;

    public IsTuruService(IsTuruRepository isTuruRepository,
                         KullaniciRepository kullaniciRepository) {
        this.isTuruRepository = isTuruRepository;
        this.kullaniciRepository = kullaniciRepository;
    }

    public List<IsTuruResponseDTO> girisYapanKullanicininIsTurleri() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getName() == null || "anonymousUser".equals(auth.getName())) {
            throw new RuntimeException("Kullanıcı giriş yapmamış");
        }

        String email = auth.getName();

        Kullanici kullanici = kullaniciRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Birim birim = kullanici.getBirim();
        if (birim == null) {
            throw new RuntimeException("Kullanıcının bağlı olduğu birim bulunamadı");
        }

        Kategori kategori = birim.getKategori();
        if (kategori == null) {
            throw new RuntimeException("Birim için kategori tanımlı değil");
        }

        return isTuruRepository.findAllByKategoriId(kategori.getKategoriId());
    }
}