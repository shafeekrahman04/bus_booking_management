package com.bus.booking.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class PublicAppController {
    private static final String PUBLIC_PATH = "public/";

    @GetMapping("/home")
    public String home() {
        return PUBLIC_PATH + "index";
    }

    @GetMapping("/routes")
    public String routes() {
        return PUBLIC_PATH + "routes";
    }

    @GetMapping("/add-booking")
    public String addBooking() {
        return PUBLIC_PATH + "add-booking";
    }

    @GetMapping("/booking-confirm")
    public String bookingConfirm() {
        return PUBLIC_PATH + "booking-confirm";
    }

    @GetMapping("/my-booking")
    public String myBooking() {
        return PUBLIC_PATH + "my-booking";
    }

    @GetMapping("/login")
    public String login() {
        return PUBLIC_PATH + "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return PUBLIC_PATH + "signup";
    }

}
