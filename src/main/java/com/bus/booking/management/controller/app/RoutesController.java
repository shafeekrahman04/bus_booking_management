package com.bus.booking.management.controller.app;

import com.bus.booking.management.dao.AdminUserRepository;
import com.bus.booking.management.dao.BookingRepository;
import com.bus.booking.management.dao.RoutesRepository;
import com.bus.booking.management.model.AdminUser;
import com.bus.booking.management.model.Bookings;
import com.bus.booking.management.model.Routes;
import com.bus.booking.management.reftype.YNStatus;
import com.bus.booking.management.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/v1")
public class RoutesController {
    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;

    @Autowired
    private AdminUserRepository adminUserRepository;
    private static final String ROUTES_PAGE = "public/routes";
    private static final String ADD_BOOKING_PAGE = "public/booking-form";
    private static final String BOOKING_SUCCESS_PAGE = "public/booking-success";
    private static final String MY_BOOKING_PAGE = "public/my-booking";

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
        List<Integer> bookedSeats = bookingRepository.findBookedSeatsByRouteId(routeId);
        model.addAttribute("route", route);
        model.addAttribute("bookedSeats", bookedSeats);

        return ADD_BOOKING_PAGE;
    }

    @PostMapping("/save-booking")
    public String saveBooking(@RequestParam("routeId") Long routeId,
                              @RequestParam("customerName") String customerName,
                              @RequestParam("customerMobile") String customerMobile,
                              @RequestParam("seats") List<Integer> seats,
                              Model model, Principal principal) {

        Routes route = routesRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        String username = principal.getName();

        AdminUser loggedInUser = adminUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));


        Bookings booking = new Bookings();
        booking.setRoute(route);
        booking.setCustomerName(customerName);
        booking.setCustomerMobile(customerMobile);
        booking.setSeatNumbers(seats);

        // calculate total price = price per seat * number of seats
        BigDecimal pricePerSeat = route.getPricePerSeat();
        BigDecimal totalPrice = pricePerSeat.multiply(BigDecimal.valueOf(seats.size()));
        booking.setTotalPrice(totalPrice);
        booking.setUser(loggedInUser);
        booking.setBookingDate(LocalDate.now());
        booking.setStatus("CONFIRMED");
        booking.setDeleted(YNStatus.NO.getStatus());
        booking.setCreatedBy("SYSTEM");
        booking.setCreatedOn(LocalDateTime.now());

        bookingRepository.save(booking);

        model.addAttribute("message", "Booking successful for " + seats.size() + " seats!");
        model.addAttribute("route", route);
        model.addAttribute("seats", seats);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("booking", booking);

        return BOOKING_SUCCESS_PAGE;
    }

    @GetMapping("/booking-confirm")
    public String bookingConfirm() {
        return BOOKING_SUCCESS_PAGE;
    }

    @GetMapping("/my-booking")
    public String getMyBookings(Model model, Principal principal) {

        if (principal == null) {
            // User not logged in → just show message
            model.addAttribute("notLoggedIn", true);
            return MY_BOOKING_PAGE;
        }

        String username = principal.getName();
        AdminUser user = adminUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Bookings> allBookings = bookingService.getBookingsByUser(user);

        LocalDate today = LocalDate.now();

        // Upcoming bookings
        List<Bookings> upcoming = allBookings.stream()
                .filter(b -> !b.getRoute().getDate().isBefore(today))
                .collect(Collectors.toList());

        // Past bookings
        List<Bookings> past = allBookings.stream()
                .filter(b -> b.getRoute().getDate().isBefore(today))
                .collect(Collectors.toList());

        model.addAttribute("upcomingBookings", upcoming);
        model.addAttribute("pastBookings", past);

        return MY_BOOKING_PAGE;
    }

    @PostMapping("/my-booking/{id}/cancel")
    public String cancelBooking(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookingService.cancelBooking(id);
        redirectAttributes.addFlashAttribute("message", "Booking cancelled successfully.");
        return "redirect:/v1/my-booking"; // redirect back to bookings page
    }
}
