package com.bus.booking.management.controller.app.admin;

import com.bus.booking.management.mapper.BusMapper;
import com.bus.booking.management.model.Bus;
import com.bus.booking.management.payload.dto.BusForm;
import com.bus.booking.management.service.bus.BusService;
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
@RequestMapping("/admin/bus")
public class BusController {
    private static final String BUS_ADD_PAGE = "admin/bus/bus-add";
    private static final String BUS_LIST_PAGE = "admin/bus/bus-list";
    private static final String BUS_EDIT_PAGE = "admin/bus/bus-edit";

    @Autowired
    @Qualifier("BusMapper")
    private BusMapper busMapper;

    @Autowired
    private BusService busService;

    @GetMapping("")
    public String busListPage(ModelMap model) {
        List<Bus> bus = busService.listAllBus();
        List<BusForm> busForms = busMapper.listBusForm(bus);
        model.addAttribute("bus", busForms);
        return BUS_LIST_PAGE;
    }

    @GetMapping("/add")
    public String showAddPage(Model model) {
        BusForm busForm = new BusForm();
        model.addAttribute("busForm", busForm);
        return BUS_ADD_PAGE;
    }

    @PostMapping("/post")
    public String saveBus(@ModelAttribute("busForm") BusForm busForm, RedirectAttributes redirectAttributes) throws IOException {
        try {
            Bus bus = busMapper.map(busForm);
            busService.saveBus(bus);
            redirectAttributes.addFlashAttribute("successMessage", "Bus added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add bus. Please try again.");
        }
        return "redirect:/admin/bus/add";
    }

    @GetMapping("/edit/{id}")
    public String editBus(Model model, @PathVariable Long id) {
        Optional<Bus> bus = busService.getBusById(id);
        BusForm busForm = busMapper.remap(bus.get());
        model.addAttribute("busForm", busForm);
        return BUS_EDIT_PAGE;
    }

    @PostMapping("/update")
    public String updateBus(@ModelAttribute("busForm") BusForm busForm, RedirectAttributes redirectAttributes) {
        try {
            Optional<Bus> getBus = busService.getBusById(Long.valueOf(busForm.getId()));
            Bus updateBus = busMapper.map(busForm, getBus.get());
            Bus savedBus = busService.updateBus(updateBus);

            redirectAttributes.addFlashAttribute("successMessage", "Bus updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update bus. Please try again.");
        }
        return "redirect:/admin/bus/edit/" + busForm.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable String id) {
        Bus bus = busService.deleteBus(Long.valueOf(id));
        return "redirect:/admin/bus";
    }
}
