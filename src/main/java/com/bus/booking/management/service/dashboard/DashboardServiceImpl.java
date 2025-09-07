package com.bus.booking.management.service.dashboard;

import com.bus.booking.management.dao.BookingRepository;
import com.bus.booking.management.dao.BusRepository;
import com.bus.booking.management.dao.RoutesRepository;
import com.bus.booking.management.model.Bookings;
import com.bus.booking.management.payload.response.DashboardResponse;
import com.bus.booking.management.reftype.YNStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public DashboardResponse getDashboardData() {
        long totalBookings = bookingRepository.count();
        long activeRoutes = routesRepository.countByStatus(YNStatus.YES.getStatus());
        long totalBuses = busRepository.count();

        List<Bookings> latestBookings = bookingRepository.findTop5ByOrderByBookingDateDesc();

        List<DashboardResponse.RecentBookingDto> recentBookings = latestBookings.stream()
                .map(b -> new DashboardResponse.RecentBookingDto(
                        b.getId(),
                        b.getRoute().getOrigin() + " to " + b.getRoute().getDestination(),
                        b.getUser().getName(),
                        b.getBookingDate().toString(),
                        b.getStatus()
                ))
                .collect(Collectors.toList());

        return new DashboardResponse(totalBookings, activeRoutes, totalBuses, recentBookings);
    }
}
