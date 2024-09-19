package com.example.parkingmanagement.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.parkingmanagement.model.Booking;
import com.example.parkingmanagement.model.Payment;
import com.example.parkingmanagement.model.User;
import com.example.parkingmanagement.repo.BookingRepository;
import com.example.parkingmanagement.repo.UserRepository;
import com.example.parkingmanagement.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingRepository bookingRepository;


    @PostMapping("/process")
    public String processPayment(@RequestBody Payment payment) {
        Booking booking = bookingRepository.findById(payment.getBooking().getId());
        payment.setAmount(booking.getAmount());
        payment.setPaymentDate(LocalDateTime.now());

        paymentService.processPayment(booking, payment);
        return "Payment processed successfully.";
    }

    @DeleteMapping("/{paymentId}")
    public String deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return "Payment deleted successfully.";
    }
}
