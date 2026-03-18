package com.example.workflowengine.controller;

import com.example.workflowengine.dto.GelenTalepResponse;
import com.example.workflowengine.dto.KullaniciTalepResponse;
import com.example.workflowengine.security.CurrentUser;
import com.example.workflowengine.service.WorkflowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talepler")
public class TalepController {

    private final WorkflowService workflowService;

    public TalepController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    // bana gelen talepler
    @GetMapping("/gelen")
    public List<GelenTalepResponse> gelen() {

        Long kullaniciId = CurrentUser.id();

        return workflowService.gelenTalepler(kullaniciId);
    }

    // benim taleplerim
    @GetMapping("/benim")
    public List<KullaniciTalepResponse> taleplerim() {

        Long kullaniciId = CurrentUser.id();

        return workflowService.kullaniciTalepleri(kullaniciId);
    }
}