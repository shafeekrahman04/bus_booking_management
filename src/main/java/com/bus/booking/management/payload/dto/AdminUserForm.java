package com.bus.booking.management.payload.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserForm {

    private String id;
    private String name;
    private String username;
    private String mobileNumber;
    private String email;
    private String password;
    private String role;

}
