package com.example.workflowengine.controller;
import com.example.workflowengine.dto.GelenTalepResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.workflowengine.dto.KullaniciTalepResponse;
import com.example.workflowengine.entity.*;
import com.example.workflowengine.security.CurrentUser;
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
    private Long getCurrentUserId(){

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return Long.parseLong(authentication.getName());
    }
    // süreç başlat
    @PostMapping("/baslat")
    public Surec baslat(
            @RequestParam Long isTuruId
    ) {

        Long kullaniciId = CurrentUser.id();

        return workflowService.baslat(kullaniciId, isTuruId);
    }

    // bana gelen talepler
    @GetMapping("/gelen")
    public List<GelenTalepResponse> gelen() {

        Long kullaniciId = CurrentUser.id();

        return workflowService.gelenTalepler(kullaniciId);
    }

    // task onayla
    @PostMapping("/onayla")
    public void onayla(@RequestParam Long taskId) {

        workflowService.onayla(taskId);
    }

    // task reddet
    @PostMapping("/reddet")
    public void reddet(
            @RequestParam Long taskId,
            @RequestParam String yorum
    ) {

        workflowService.reddet(taskId, yorum);
    }

    // kullanıcının kendi talepleri
    @GetMapping("/taleplerim")
    public List<KullaniciTalepResponse> taleplerim() {

        Long kullaniciId = CurrentUser.id();

        return workflowService.kullaniciTalepleri(kullaniciId);
    }
}