package com.bus.booking.management.service.booking;

import com.bus.booking.management.dao.BookingRepository;
import com.bus.booking.management.model.AdminUser;
import com.bus.booking.management.model.Bookings;
import com.bus.booking.management.reftype.YNStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Bookings> listAllBooking() {
        return bookingRepository.findAllByDeleted(YNStatus.NO.getStatus());
    }

    @Override

    public List<Bookings> getBookingsByUser(AdminUser user) {
        return bookingRepository.findByUserId(user.getId());
    }

    @Override
    public Optional<Bookings> getBookingByIdAndUser(Long id, String username) {
        return bookingRepository.findByIdAndUserUsername(id, username);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Bookings booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking id: " + bookingId));

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }
}
