package com.bus.booking.management.mapper;

import com.bus.booking.management.model.AdminUser;
import com.bus.booking.management.payload.dto.AdminUserForm;
import com.bus.booking.management.reftype.YNStatus;
import com.bus.booking.management.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("AdminUserMapper")
public class AdminUserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Convert Entity -> DTO
    public AdminUserForm remap(AdminUser adminUser) {
        AdminUserForm adminUserForm = new AdminUserForm();
        adminUserForm.setId(String.valueOf(adminUser.getId()));

        adminUserForm.setUsername(adminUser.getUsername());
        adminUserForm.setName(adminUser.getName());
        adminUserForm.setEmail(adminUser.getEmail());
        adminUserForm.setMobileNumber(adminUser.getMobileNumber());
        adminUserForm.setRole(adminUser.getRole());
        return adminUserForm;
    }

    // Convert List<Entity> -> List<DTO>
    public List<AdminUserForm> listAdminUserForm(List<AdminUser> adminUsers) {
        List<AdminUserForm> adminUserForms = new ArrayList<>();
        for (AdminUser adminUser : adminUsers) {
            AdminUserForm adminUserForm = remap(adminUser);
            adminUserForms.add(adminUserForm);
        }
        return adminUserForms;
    }

    public AdminUser map(AdminUserForm adminUserForm) {
        String encodedPassword = passwordEncoder.encode(adminUserForm.getPassword());
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(adminUserForm.getUsername());
        adminUser.setName(adminUserForm.getName());
        adminUser.setEmail(adminUserForm.getEmail());
        adminUser.setMobileNumber(adminUserForm.getMobileNumber());
        adminUser.setPassword(encodedPassword);
        adminUser.setRole(adminUserForm.getRole());
        adminUser.setDeleted(YNStatus.NO.getStatus());
        adminUser.setCreatedBy(StringUtils.user);
        adminUser.setCreatedOn(StringUtils.now);
        return adminUser;
    }

    public AdminUser signup(AdminUserForm adminUserForm) {
        String encodedPassword = passwordEncoder.encode(adminUserForm.getPassword());
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(adminUserForm.getUsername());
        adminUser.setName(adminUserForm.getName());
        adminUser.setEmail(adminUserForm.getEmail());
        adminUser.setMobileNumber(adminUserForm.getMobileNumber());
        adminUser.setPassword(encodedPassword);
        adminUser.setRole("USER");
        adminUser.setDeleted(YNStatus.NO.getStatus());
        adminUser.setCreatedBy(StringUtils.user);
        adminUser.setCreatedOn(StringUtils.now);
        return adminUser;
    }

    public AdminUser map(AdminUserForm adminUserForm, AdminUser adminUser) {
        String encodedPassword = passwordEncoder.encode(adminUserForm.getPassword());

        adminUser.setUsername(adminUserForm.getUsername());
        adminUser.setRole(adminUserForm.getRole());
        adminUser.setName(adminUserForm.getName());
        adminUser.setEmail(adminUserForm.getEmail());
        adminUser.setMobileNumber(adminUserForm.getMobileNumber());
        adminUser.setPassword(encodedPassword);
        adminUser.setDeleted(YNStatus.NO.getStatus());
        adminUser.setUpdatedBy(StringUtils.user);
        adminUser.setUpdatedOn(StringUtils.now);
        return adminUser;
    }
}
