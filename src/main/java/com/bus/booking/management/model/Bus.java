package com.bus.booking.management.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BUS_NAME")
    private String busName;

    @Column(name = "BUS_NUMBER")
    private String busNumber;

    @Column(name = "BUS_TYPE")
    private String busType;

    @Column(name = "CAPACITY")
    private String capacity;
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
