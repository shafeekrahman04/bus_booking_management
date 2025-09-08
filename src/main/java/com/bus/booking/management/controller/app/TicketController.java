package com.bus.booking.management.controller.app;

import com.bus.booking.management.model.Bookings;
import com.bus.booking.management.service.booking.BookingService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.security.Principal;

@Controller
@RequestMapping("/v1/ticket")
public class TicketController {

    @Autowired
    private BookingService bookingService;

    private static final String TICKET_PAGE = "public/ticket";

    // Show ticket page
    @GetMapping("/{id}")
    public String viewTicket(@PathVariable Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login"; // force login if not logged in
        }

        String username = principal.getName();
        Bookings booking = bookingService.getBookingByIdAndUser(id, username)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        model.addAttribute("booking", booking);
        return TICKET_PAGE; // maps to ticket.html
    }

    // Generate QR code
    @GetMapping("/qrcode/{id}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable Long id, Principal principal) throws Exception {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = principal.getName();
        Bookings booking = bookingService.getBookingByIdAndUser(id, username)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        String qrText = "Booking ID: " + booking.getId() +
                " | Route: " + booking.getRoute().getOrigin() + " → " + booking.getRoute().getDestination() +
                " | Date: " + booking.getRoute().getDate() +
                " | Seats: " + booking.getSeatNumbers();

        // Use ZXing to generate QR
        int size = 250;
        BitMatrix bitMatrix = new QRCodeWriter().encode(qrText, BarcodeFormat.QR_CODE, size, size);

        ByteArrayOutputStream pngOutput = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutput);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(pngOutput.toByteArray());
    }
}
