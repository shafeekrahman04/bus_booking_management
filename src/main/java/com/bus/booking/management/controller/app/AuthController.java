package com.bus.booking.management.controller.app;

import com.bus.booking.management.mapper.AdminUserMapper;
import com.bus.booking.management.model.AdminUser;
import com.bus.booking.management.payload.dto.AdminUserForm;
import com.bus.booking.management.service.admin_user.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/v1")
public class AuthController {
    @Autowired
    @Qualifier("AdminUserMapper")
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminUserService adminUserService;
    private static final String PUBLIC_PATH = "public/";



    @GetMapping("/login")
    public String login() {
        return PUBLIC_PATH + "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("adminUserForm", new AdminUserForm());
        return PUBLIC_PATH + "signup";
    }

    @PostMapping("/signup")
    public String saveAdminUser(@ModelAttribute("adminUserForm") AdminUserForm adminUserForm,
                                RedirectAttributes redirectAttributes) {
        try {
            AdminUser adminUser = adminUserMapper.signup(adminUserForm);
            adminUserService.saveAdminUser(adminUser);
            redirectAttributes.addFlashAttribute("successMessage", "Account created successfully!");
            return "redirect:/v1/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create account. Please try again.");
            return "redirect:/v1/signup";
        }
    }

}
