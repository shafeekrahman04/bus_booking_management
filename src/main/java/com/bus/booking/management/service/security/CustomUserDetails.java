package com.bus.booking.management.service.security;


import com.bus.booking.management.model.AdminUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
public class CustomUserDetails implements UserDetails {

    private final AdminUser adminUser;

    public CustomUserDetails(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + adminUser.getRole()));
    }


    @Override
    public String getPassword() {
        return adminUser.getPassword();
    }

    @Override
    public String getUsername() {
        return adminUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return !"Y".equalsIgnoreCase(adminUser.getDeleted());
    }
}
