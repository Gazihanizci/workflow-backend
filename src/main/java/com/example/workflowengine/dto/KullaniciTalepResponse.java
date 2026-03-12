package com.example.workflowengine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KullaniciTalepResponse {

    private Long surecId;
    private String surecDurum;
    private Long taskId;
    private String taskDurum;
    private String redYorum;
}