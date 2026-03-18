package com.example.workflowengine.controller;

import com.example.workflowengine.dto.SurecBaslatRequest;
import com.example.workflowengine.entity.Surec;
import com.example.workflowengine.security.CurrentUser;
import com.example.workflowengine.service.WorkflowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    // süreç başlat
    @PostMapping("/baslat")
    public Surec baslat(@RequestBody SurecBaslatRequest request) {

        Long kullaniciId = CurrentUser.id();

        return workflowService.baslat(
                kullaniciId,
                request.getIsTuruId(),
                request.getAciklama()
        );
    }
}