package com.example.workflowengine.service;

import com.example.workflowengine.dto.KullaniciResponseDTO;
import com.example.workflowengine.repository.KullaniciRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class KullaniciService {

    private final KullaniciRepository kullaniciRepository;

    public KullaniciService(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    public KullaniciResponseDTO currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            throw new RuntimeException("Kullanıcı giriş yapmamış");
        }

        String email = auth.getName();

        return kullaniciRepository.kullaniciBilgisi(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }
}