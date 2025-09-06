package com.bus.booking.management.payload.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookingForm {

    private String id;
    private String userId;
    private String routeId;
    private String totalPrice;
    private String bookingDate;
    private String status;
    private String busNumber;
    private String passengerName;

}
