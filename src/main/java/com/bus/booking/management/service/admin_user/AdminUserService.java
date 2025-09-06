package com.bus.booking.management.service.admin_user;

import com.bus.booking.management.model.AdminUser;

import java.util.List;
import java.util.Optional;

public interface AdminUserService {
    public List<AdminUser> listAllAdminUsers();

    public AdminUser saveAdminUser(AdminUser adminUser);

    public Optional<AdminUser> getAdminUserById(Long id);

    public AdminUser updateAdminUser(AdminUser adminUser);

    public AdminUser deleteAdminUser(Long id);
}
