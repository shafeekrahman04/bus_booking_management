package com.bus.booking.management.mapper;

import com.bus.booking.management.model.Bookings;
import com.bus.booking.management.payload.dto.BookingForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("BookingMapper")
public class BookingMapper {

    // Convert Entity -> DTO
    public BookingForm remap(Bookings bookings) {
        BookingForm bookingsForm = new BookingForm();
        bookingsForm.setId(String.valueOf(bookings.getId()));
        bookingsForm.setUserId(String.valueOf(bookings.getUserId()));
        bookingsForm.setRouteId(String.valueOf(bookings.getRouteId()));
        bookingsForm.setTotalPrice(bookings.getTotalPrice() != null ? bookings.getTotalPrice().toString() : null);
        bookingsForm.setStatus(bookings.getStatus());
        bookingsForm.setBookingDate(bookings.getBookingDate() != null ? bookings.getBookingDate().toString() : null);

        if (bookings.getRoute() != null && bookings.getRoute().getBus() != null) {
            bookingsForm.setBusNumber(bookings.getRoute().getBus().getBusNumber());
        }
        if (bookings.getUser() != null) {
            bookingsForm.setPassengerName(bookings.getUser().getName());
        }
        return bookingsForm;
    }

    // Convert List<Entity> -> List<DTO>
    public List<BookingForm> listBookingsForm(List<Bookings> bookings) {
        List<BookingForm> bookingsForms = new ArrayList<>();
        for (Bookings route : bookings) {
            BookingForm bookingsForm = remap(route);
            bookingsForms.add(bookingsForm);
        }
        return bookingsForms;
    }

}
