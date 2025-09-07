package com.bus.booking.management.payload.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoutesForm {
    private String id;
    private String busId;
    private String busNumber;
    private String pricePerSeat;
    private String origin;
    private String destination;
    private String distance;
    private String date;
    private String pickupTime;
    private String dropTime;
    private String status;

}
