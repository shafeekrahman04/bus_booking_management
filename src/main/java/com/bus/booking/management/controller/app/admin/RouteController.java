package com.bus.booking.management.controller.app.admin;

import com.bus.booking.management.mapper.RoutesMapper;
import com.bus.booking.management.model.Bus;
import com.bus.booking.management.model.Routes;
import com.bus.booking.management.payload.dto.RoutesForm;
import com.bus.booking.management.service.bus.BusService;
import com.bus.booking.management.service.routes.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/routes")
public class RouteController {
    private static final String ROUTE_LIST_PAGE = "admin/routes/route-list";
    private static final String ROUTES_ADD_PAGE = "admin/routes/route-add";
    private static final String ROUTES_EDIT_PAGE = "admin/routes/route-edit";

    @Autowired
    @Qualifier("RouteMapper")
    private RoutesMapper routeMapper;

    @Autowired
    private RoutesService routesService;

    @Autowired
    private BusService busService;

    @GetMapping
    public String routeListPage(ModelMap model) {
        List<Routes> route = routesService.listAllRoutes();
        List<RoutesForm> routeForms = routeMapper.listRoutesForm(route);
        model.addAttribute("route", routeForms);
        return ROUTE_LIST_PAGE;
    }

    @GetMapping("/add")
    public String showAddPage(Model model) {
        List<Bus> buses = busService.listAllBus();
        RoutesForm routesForm = new RoutesForm();
        model.addAttribute("routesForm", routesForm);
        model.addAttribute("buses", buses);
        return ROUTES_ADD_PAGE;
    }


    @PostMapping("/post")
    public String saveRoutes(@ModelAttribute("routesForm") RoutesForm routesForm, RedirectAttributes redirectAttributes) throws IOException {
        try {
            Routes routes = routeMapper.map(routesForm);
            routesService.saveRoutes(routes);
            redirectAttributes.addFlashAttribute("successMessage", "Routes added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add routes. Please try again.");
        }
        return "redirect:/admin/routes/add";
    }

    @GetMapping("/edit/{id}")
    public String editRoutes(Model model, @PathVariable Long id) {
        List<Bus> buses = busService.listAllBus();
        Optional<Routes> routes = routesService.getRoutesById(id);
        RoutesForm routesForm = routeMapper.remap(routes.get());
        model.addAttribute("routesForm", routesForm);
        model.addAttribute("buses", buses);
        return ROUTES_EDIT_PAGE;
    }

    @PostMapping("/update")
    public String updateRoutes(@ModelAttribute("routesForm") RoutesForm routesForm, RedirectAttributes redirectAttributes) {
        try {
            Optional<Routes> getRoutes = routesService.getRoutesById(Long.valueOf(routesForm.getId()));
            Routes updateRoutes = routeMapper.map(routesForm, getRoutes.get());
            Routes savedRoutes = routesService.updateRoutes(updateRoutes);

            redirectAttributes.addFlashAttribute("successMessage", "Routes updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update routes. Please try again.");
        }
        return "redirect:/admin/routes/edit/" + routesForm.getId();
    }
}
