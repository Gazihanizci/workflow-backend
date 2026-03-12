package com.example.workflowengine.controller;

import com.example.workflowengine.dto.IsAkisiResponse;
import com.example.workflowengine.service.IsAkisiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/is-akislari")
public class IsAkisiController {

    private final IsAkisiService isAkisiService;

    public IsAkisiController(IsAkisiService isAkisiService) {
        this.isAkisiService = isAkisiService;
    }

    @GetMapping
    public List<IsAkisiResponse> getAll() {
        return isAkisiService.getIsAkislari();
    }
}