package com.bus.booking.management.controller.app.admin;

import com.bus.booking.management.mapper.BookingMapper;
import com.bus.booking.management.model.Bookings;
import com.bus.booking.management.payload.dto.BookingForm;
import com.bus.booking.management.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/bookings")
public class BookingController {
    private static final String BOOKING_LIST_PAGE = "admin/booking/booking-list";

    @Autowired
    @Qualifier("BookingMapper")
    private BookingMapper bookingMapper;

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String bookingListPage(ModelMap model) {
        List<Bookings> booking = bookingService.listAllBooking();
        List<BookingForm> bookingForms = bookingMapper.listBookingsForm(booking);
        model.addAttribute("booking", bookingForms);
        return BOOKING_LIST_PAGE;
    }
}
