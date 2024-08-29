package com.example.summary.dto;

import lombok.Data;

@Data
public class ConsumptionDTO {
    private String name;
    private int countConsumption;
    private int cost;

    public ConsumptionDTO(String name, int countConsumption, int cost){
        this.name = name;
        this.countConsumption = countConsumption;
        this.cost = cost;
    }
}
