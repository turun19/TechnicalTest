package com.example.summary.service;

import com.example.summary.dto.ConsumptionDTO;
import com.example.summary.dto.SummaryDTO;
import com.example.summary.model.Consumption;
import com.example.summary.model.PriceItem;
import lombok.extern.slf4j.Slf4j;
import com.example.summary.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SummaryService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Booking> getBookingList(){
        String url = "https://66876cc30bc7155dc017a662.mockapi.io/api/dummy-data/bookingList";
        Booking[] bookings = restTemplate.getForObject(url, Booking[].class);
        return List.of(bookings);
    }

    public List<PriceItem> getPriceList(){
        String url = "https://6686cb5583c983911b03a7f3.mockapi.io/api/dummy-data/masterJenisKonsumsi";
        PriceItem[] priceItems = restTemplate.getForObject(url, PriceItem[].class);
        return List.of(priceItems);
    }

    public List<SummaryDTO> getSummary(){
        List<Booking> bookings = getBookingList();
        List<PriceItem> priceItems = getPriceList();
        List<SummaryDTO> summary = new ArrayList<>();

        for (Booking booking : bookings) {
            int totalCost = 0;
            List<ConsumptionDTO> consumptionDTOList = new ArrayList<>();

            for (Consumption consumption : booking.getListConsumption()) {
                for (PriceItem priceItem : priceItems) {
                    if (consumption.getName().equals(priceItem.getName())) {
                        int cost = priceItem.getMaxPrice() * booking.getParticipants();
                        totalCost += cost;
                        consumptionDTOList.add(new ConsumptionDTO(consumption.getName(), booking.getParticipants(), cost));
                    }
                }
            }

            double usagePercentage = calculateUsagePercentage(booking);
            summary.add(new SummaryDTO(booking.getId(),booking.getRoomName(), usagePercentage, totalCost, consumptionDTOList));
        }
        return summary;
        }

    private double calculateUsagePercentage(Booking booking) {
        long totalTime = Duration.between(Instant.parse(booking.getStartTime()), Instant.parse(booking.getEndTime())).toMinutes();
        long maxTime = 24 * 60;
        double percent = (double) totalTime / maxTime * 100;
        return Math.round(percent * 100) / 100;
    }
    }


