package com.bus.booking.management.controller.admin;

import com.bus.booking.management.mapper.AdminUserMapper;
import com.bus.booking.management.model.AdminUser;
import com.bus.booking.management.payload.dto.AdminUserForm;
import com.bus.booking.management.service.admin_user.AdminUserService;
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
@RequestMapping("admin/user")
public class AdminUserController {

    private static final String ADMIN_USER_LIST_PAGE = "admin/admin-user/admin-user-list";
    private static final String ADMIN_USER_ADD_PAGE = "admin/admin-user/admin-user-add";
    private static final String ADMIN_USER_EDIT_PAGE = "admin/admin-user/admin-user-edit";

    @Autowired
    @Qualifier("AdminUserMapper")
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping
    public String adminUserListPage(ModelMap model) {
        List<AdminUser> adminUser = adminUserService.listAllAdminUsers();
        List<AdminUserForm> adminUserForms = adminUserMapper.listAdminUserForm(adminUser);
        model.addAttribute("adminUser", adminUserForms);
        return ADMIN_USER_LIST_PAGE;
    }

    @GetMapping("/add")
    public String showAddPage(Model model) {
        AdminUserForm adminUserForm = new AdminUserForm();
        model.addAttribute("adminUserForm", adminUserForm);
        return ADMIN_USER_ADD_PAGE;
    }

    @PostMapping("/post")
    public String saveAdminUser(@ModelAttribute("adminUserForm") AdminUserForm adminUserForm, RedirectAttributes redirectAttributes) throws IOException {
        try {
            AdminUser adminUser = adminUserMapper.map(adminUserForm);
            adminUserService.saveAdminUser(adminUser);
            redirectAttributes.addFlashAttribute("successMessage", "AdminUser added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add adminUser. Please try again.");
        }
        return "redirect:/admin/user/add";
    }

    @GetMapping("/edit/{id}")
    public String editAdminUser(Model model, @PathVariable Long id) {
        Optional<AdminUser> adminUser = adminUserService.getAdminUserById(id);
        AdminUserForm adminUserForm = adminUserMapper.remap(adminUser.get());
        model.addAttribute("adminUserForm", adminUserForm);
        return ADMIN_USER_EDIT_PAGE;
    }

    @PostMapping("/update")
    public String updateAdminUser(@ModelAttribute("adminUserForm") AdminUserForm adminUserForm, RedirectAttributes redirectAttributes) {
        try {
            Optional<AdminUser> getAdminUser = adminUserService.getAdminUserById(Long.valueOf(adminUserForm.getId()));
            AdminUser updateAdminUser = adminUserMapper.map(adminUserForm, getAdminUser.get());
            AdminUser savedAdminUser = adminUserService.updateAdminUser(updateAdminUser);

            redirectAttributes.addFlashAttribute("successMessage", "AdminUser updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update adminUser. Please try again.");
        }
        return "redirect:/admin/user/edit/" + adminUserForm.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteAdminUser(@PathVariable String id) {
        AdminUser adminUser = adminUserService.deleteAdminUser(Long.valueOf(id));
        return "redirect:/admin/user";
    }
}
