package com.example.summary.model;

import lombok.Data;

import java.util.List;

@Data
public class Booking {
    private String bookingDate;
    private String officeName;
    private String startTime;
    private String endTime;
    private List<Consumption> listConsumption;
    private int participants;
    private String roomName;
    private String id;
}
