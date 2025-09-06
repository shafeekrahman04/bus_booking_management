package com.bus.booking.management.service.admin_user;

import com.bus.booking.management.dao.AdminUserRepository;
import com.bus.booking.management.model.AdminUser;
import com.bus.booking.management.reftype.YNStatus;
import com.bus.booking.management.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;


    @Override
    public List<AdminUser> listAllAdminUsers() {
        return adminUserRepository.findAllByDeleted(YNStatus.NO.getStatus());
    }

    @Override
    public AdminUser saveAdminUser(AdminUser adminUser) {
        return adminUserRepository.save(adminUser);
    }

    @Override
    public Optional<AdminUser> getAdminUserById(Long id) {
        return adminUserRepository.findById(id);
    }

    @Override
    public AdminUser updateAdminUser(AdminUser adminUser) {
        return adminUserRepository.save(adminUser);
    }

    @Override
    public AdminUser deleteAdminUser(Long id) {
        Optional<AdminUser> adminUserOptional = adminUserRepository.findById(id);
        if (adminUserOptional.isPresent()) {
            AdminUser adminUser = adminUserOptional.get();
            adminUser.setUpdatedBy(StringUtils.user);
            adminUser.setUpdatedOn(StringUtils.now);
            adminUser.setDeleted(YNStatus.YES.getStatus());
            adminUserRepository.save(adminUser);
            return adminUser;
        }
        return null;
    }
}
