package com.bus.booking.management.dao;

import com.bus.booking.management.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    List<AdminUser> findAllByRoleNotAndDeleted(String role, String deleted);
    Optional<AdminUser> findByUsername(String username);


}
