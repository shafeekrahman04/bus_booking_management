package com.bus.booking.management.controller.app;

import com.bus.booking.management.dao.BookingRepository;
import com.bus.booking.management.dao.RoutesRepository;
import com.bus.booking.management.model.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/v1")
public class RoutesController {
    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private BookingRepository bookingRepository;
    private static final String ROUTES_PAGE = "public/routes";
    private static final String ADD_BOOKING_PAGE = "public/booking-form";

    @GetMapping("/routes")
    public String routes(@RequestParam(required = false) String origin,
                         @RequestParam(required = false) String destination,
                         @RequestParam(required = false) String date,
                         Model model) {

        List<Routes> routesList = List.of();

        if (origin != null && destination != null && date != null) {
            LocalDate travelDate = LocalDate.parse(date);
            routesList = routesRepository.findByOriginAndDestinationAndDate(origin, destination, travelDate);
        }

        model.addAttribute("origin", origin != null ? origin : "");
        model.addAttribute("destination", destination != null ? destination : "");
        model.addAttribute("date", date != null ? date : "");
        model.addAttribute("routesList", routesList);
        return ROUTES_PAGE;
    }

    @GetMapping("/add-booking")
    public String addBooking(@RequestParam("routeId") Long routeId, Model model) {
        Routes route = routesRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        // Example: bookedSeats = ["1C","2B","4A"]
        List<String> bookedSeats = bookingRepository.findBookedSeatsByRouteId(routeId);

        model.addAttribute("route", route);
        model.addAttribute("bookedSeats", bookedSeats);

        return ADD_BOOKING_PAGE;
    }
}
