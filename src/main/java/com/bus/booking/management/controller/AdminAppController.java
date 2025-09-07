package com.bus.booking.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminAppController {

    private static final String ADMIN_PATH = "admin/";

    @GetMapping("/login")
    public String login() {
        return ADMIN_PATH + "login";
    }


}
