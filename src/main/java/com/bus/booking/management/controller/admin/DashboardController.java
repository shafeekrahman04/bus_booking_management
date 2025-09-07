package com.bus.booking.management.controller.admin;

import com.bus.booking.management.payload.response.DashboardResponse;
import com.bus.booking.management.service.dashboard.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {

    private static final String DASHBOARD = "admin/dashboard";

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public String getDashboardPage(ModelMap model) {
        DashboardResponse dashboardData = dashboardService.getDashboardData();
        model.addAttribute("dashboard", dashboardData);

        return DASHBOARD;
    }
}
