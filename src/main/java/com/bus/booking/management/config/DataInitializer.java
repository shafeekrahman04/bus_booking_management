package com.bus.booking.management.config;

import com.bus.booking.management.dao.AdminUserRepository;
import com.bus.booking.management.model.AdminUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(AdminUserRepository adminUserRepository,
                                       PasswordEncoder passwordEncoder) {
        return args -> {
            String defaultUsername = "admin";

            if (adminUserRepository.findByUsername(defaultUsername).isEmpty()) {
                AdminUser admin = AdminUser.builder()
                        .username(defaultUsername)
                        .name("System Administrator")
                        .mobileNumber("9999999999")
                        .email("admin@busbooking.com")
                        .password(passwordEncoder.encode("123"))
                        .role("ADMIN")
                        .deleted("N")
                        .createdBy("SYSTEM")
                        .createdOn(LocalDateTime.now())
                        .build();

                adminUserRepository.save(admin);
                System.out.println("✅ Default Admin User inserted with encoded password");
            } else {
                System.out.println("ℹ️ Admin User already exists, skipping insert");
            }
        };
    }
}
