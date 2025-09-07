package com.bus.booking.management.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private long totalBookings;
    private long activeRoutes;
    private long newUsers;
    private List<RecentBookingDto> recentBookings;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecentBookingDto {
        private Long bookingId;
        private String route;
        private String user;
        private String date;
        private String status;
    }
}
