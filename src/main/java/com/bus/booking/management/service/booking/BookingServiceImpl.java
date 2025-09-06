package com.bus.booking.management.service.booking;

import com.bus.booking.management.dao.BookingRepository;
import com.bus.booking.management.model.Bookings;
import com.bus.booking.management.reftype.YNStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Bookings> listAllBooking() {
        return bookingRepository.findAllByDeleted(YNStatus.NO.getStatus());
    }
}
