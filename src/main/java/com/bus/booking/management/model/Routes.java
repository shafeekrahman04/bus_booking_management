package com.bus.booking.management.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "routes")
public class Routes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BUS_ID")
    private String busId;

    @Column(name = "PRICE_PER_SEAT")
    private BigDecimal pricePerSeat;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "DISTANCE")
    private String distance;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "PICKUP_TIME")
    private LocalTime pickupTime;

    @Column(name = "DROP_TIME")
    private LocalTime dropTime;

    @Column(name = "DELETED")
    private String deleted;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;

    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;
}
