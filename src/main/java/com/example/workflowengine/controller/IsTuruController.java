package com.example.workflowengine.controller;

import com.example.workflowengine.dto.IsTuruResponseDTO;
import com.example.workflowengine.service.IsTuruService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/is-turleri")
@CrossOrigin(origins = "http://localhost:5173")
public class IsTuruController {

    private final IsTuruService isTuruService;

    public IsTuruController(IsTuruService isTuruService) {
        this.isTuruService = isTuruService;
    }

    @GetMapping("/benim-birimim")
    public List<IsTuruResponseDTO> benimBirimiminIsTurleri() {
        return isTuruService.girisYapanKullanicininIsTurleri();
    }
}