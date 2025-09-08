package com.bus.booking.management.dao;

import com.bus.booking.management.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {

    List<Bookings> findAllByDeleted(String deleted);

    List<Bookings> findTop5ByOrderByBookingDateDesc();

    @Query("SELECT s FROM Bookings b JOIN b.seatNumbers s WHERE b.route.id = :routeId AND b.deleted = 'F'")
    List<Integer> findBookedSeatsByRouteId(Long routeId);

    List<Bookings> findByUserId(Long userId);

    Optional<Bookings> findByIdAndUserUsername(Long id, String username);


}
