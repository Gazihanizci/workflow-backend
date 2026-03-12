package com.example.workflowengine.service;

import com.example.workflowengine.dto.IsAkisiResponse;
import com.example.workflowengine.repository.IsAkisiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IsAkisiService {

    private final IsAkisiRepository isAkisiRepository;

    public IsAkisiService(IsAkisiRepository isAkisiRepository) {
        this.isAkisiRepository = isAkisiRepository;
    }

    public List<IsAkisiResponse> getIsAkislari() {
        return isAkisiRepository.findAllIsAkislari();
    }
}