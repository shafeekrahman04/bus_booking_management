package com.bus.booking.management.controller.app.admin;

import com.bus.booking.management.mapper.RoutesMapper;
import com.bus.booking.management.model.Routes;
import com.bus.booking.management.payload.dto.RoutesForm;
import com.bus.booking.management.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/routes")
public class RouteController {
    private static final String ROUTE_LIST_PAGE = "admin/routes/route-list";

    @Autowired
    @Qualifier("RouteMapper")
    private RoutesMapper routeMapper;

    @Autowired
    private RoutesService routesService;

    @GetMapping
    public String routeListPage(ModelMap model) {
        List<Routes> route = routesService.listAllRoutes();
        List<RoutesForm> routeForms = routeMapper.listRoutesForm(route);
        model.addAttribute("route", routeForms);
        return ROUTE_LIST_PAGE;
    }
}
