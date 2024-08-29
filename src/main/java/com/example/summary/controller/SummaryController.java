package com.example.summary.controller;

import com.example.summary.dto.SummaryDTO;
import com.example.summary.model.Booking;
import com.example.summary.model.PriceItem;
import com.example.summary.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SummaryController {
    @Autowired
    SummaryService service;

    @GetMapping
    public List<Booking> getBooking(){
        return service.getBookingList();
    }

    @GetMapping("/price")
    public List<PriceItem> getPrice(){
        return service.getPriceList();
    }

    @GetMapping("/dashboard/summary")
    public List<SummaryDTO> getSummary(){
        return service.getSummary();
    }
}
