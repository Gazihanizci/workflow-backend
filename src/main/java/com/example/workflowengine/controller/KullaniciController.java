package com.example.workflowengine.controller;

import com.example.workflowengine.dto.KullaniciResponseDTO;
import com.example.workflowengine.service.KullaniciService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kullanicilar")
@CrossOrigin(origins = "http://localhost:5173")
public class KullaniciController {

    private final KullaniciService kullaniciService;

    public KullaniciController(KullaniciService kullaniciService) {
        this.kullaniciService = kullaniciService;
    }

    @GetMapping("/me")
    public KullaniciResponseDTO currentUser() {
        return kullaniciService.currentUser();
    }
}