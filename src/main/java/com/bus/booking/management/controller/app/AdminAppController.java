package com.bus.booking.management.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminAppController {

    private static final String ADMIN_PATH = "admin/";

    @GetMapping("/login")
    public String login() {
        return ADMIN_PATH + "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return ADMIN_PATH + "dashboard";
    }

    @GetMapping("/buses")
    public String busList() {
        return ADMIN_PATH + "bus-list";
    }

    @GetMapping("/routes")
    public String routeList() {
        return ADMIN_PATH + "route-list";
    }

    @GetMapping("/bookings")
    public String bookingList() {
        return ADMIN_PATH + "booking-list";
    }
}
