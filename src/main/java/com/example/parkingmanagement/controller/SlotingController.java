package com.example.parkingmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.parkingmanagement.model.SlotBookingRequest;
import com.example.parkingmanagement.model.Sloting;
import com.example.parkingmanagement.service.SlotingService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/slotsing")
@CrossOrigin(origins = "http://localhost:3000")
public class SlotingController {

    @Autowired
    private SlotingService slotservice;

    @GetMapping("/available")
    public List<Sloting> getAvailableSlots(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return slotservice.getAvailableSlots(date);
    }

    @PostMapping("/check")
    public String checkAvailability(@RequestBody SlotBookingRequest request) {
        boolean isAvailable = slotservice.checkSlotAvailability(request.getSlotId(), request.getDate(),
                request.getStartTime(), request.getEndTime());
        return isAvailable ? "Slot is available!" : "Slot is not available or the details do not match!";
    }

    @PostMapping("/book")
    public String bookSlot(@RequestBody SlotBookingRequest request) {
        boolean success = slotservice.bookSlot(request.getSlotId(), request.getDate(), request.getStartTime(),
                request.getEndTime());
        return success ? "Slot booked successfully!" : "Slot is not available or the details do not match!";
    }
}
