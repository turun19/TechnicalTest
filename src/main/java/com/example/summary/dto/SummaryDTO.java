package com.example.summary.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class SummaryDTO {
    private String id;
    private String roomName;
    private double usagePercentage;
    private int resultConsumptionCost;
    private List<ConsumptionDTO> detail;

    public SummaryDTO(String id,String roomName, double usagePercentage, int resultConsumptionCost, List<ConsumptionDTO> detail){
        this.id = id;
        this.roomName = roomName;
        this.usagePercentage = usagePercentage;
        this.resultConsumptionCost = resultConsumptionCost;
        this.detail = detail;
    }
}
