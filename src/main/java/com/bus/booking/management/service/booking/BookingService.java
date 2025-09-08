package com.bus.booking.management.service.booking;

import com.bus.booking.management.model.AdminUser;
import com.bus.booking.management.model.Bookings;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    public List<Bookings> listAllBooking();
    public List<Bookings> getBookingsByUser(AdminUser user);

    public Optional<Bookings> getBookingByIdAndUser(Long id, String username);

    public void cancelBooking(Long bookingId);

    public Bookings getBookingById(Long id);
}
