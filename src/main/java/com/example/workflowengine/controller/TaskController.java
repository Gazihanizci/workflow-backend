package com.example.workflowengine.controller;

import com.example.workflowengine.service.WorkflowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final WorkflowService workflowService;

    public TaskController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    // task onayla
    @PostMapping("/{taskId}/onayla")
    public void onayla(@PathVariable Long taskId) {
        workflowService.onayla(taskId);
    }

    // task reddet
    @PostMapping("/{taskId}/reddet")
    public void reddet(
            @PathVariable Long taskId,
            @RequestParam String yorum
    ) {
        workflowService.reddet(taskId, yorum);
    }
}