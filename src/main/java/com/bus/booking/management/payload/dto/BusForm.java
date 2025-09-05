package com.bus.booking.management.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusForm {
    private String id;
    private String busName;
    private String busNumber;
    private String busType;
    private String capacity;
}
