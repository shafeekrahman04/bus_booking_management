package com.bus.booking.management.dao;

import com.bus.booking.management.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {

    List<Bookings> findAllByDeleted(String deleted);

}
