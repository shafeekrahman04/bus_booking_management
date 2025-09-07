package com.bus.booking.management.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class AdminAppController {

    private static final String ADMIN_PATH = "admin/";

    @GetMapping("/login")
    public String login() {
        return ADMIN_PATH + "login";
    }


    @GetMapping("/buses")
    public String busList() {
        return ADMIN_PATH + "bus-list";
    }

}
