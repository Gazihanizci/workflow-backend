package com.example.workflowengine.controller;

import com.example.workflowengine.entity.*;
import com.example.workflowengine.service.WorkflowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping("/baslat")
    public Surec baslat(
            @RequestParam Long kullaniciId,
            @RequestParam Long isTuruId
    ) {
        return workflowService.baslat(kullaniciId, isTuruId);
    }

    @GetMapping("/gelen")
    public List<Task> gelen(@RequestParam Long kullaniciId) {

        return workflowService.gelenTalepler(kullaniciId);
    }

    @PostMapping("/onayla")
    public void onayla(@RequestParam Long taskId) {

        workflowService.onayla(taskId);
    }
}