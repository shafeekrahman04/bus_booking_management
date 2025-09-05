package com.bus.booking.management.mapper;

import com.bus.booking.management.model.Routes;
import com.bus.booking.management.payload.dto.RoutesForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("RouteMapper")
public class RoutesMapper {

    // Convert Entity -> DTO
    public RoutesForm remap(Routes route) {
        RoutesForm routesForm = new RoutesForm();
        routesForm.setId(String.valueOf(route.getId()));
        routesForm.setBusId(route.getBusId());
        routesForm.setPricePerSeat(route.getPricePerSeat() != null ? route.getPricePerSeat().toString() : null);
        routesForm.setOrigin(route.getOrigin());
        routesForm.setDestination(route.getDestination());
        routesForm.setDistance(route.getDistance());
        routesForm.setDate(route.getDate() != null ? route.getDate().toString() : null);
        routesForm.setPickupTime(route.getPickupTime() != null ? route.getPickupTime().toString() : null);
        routesForm.setDropTime(route.getDropTime() != null ? route.getDropTime().toString() : null);
        return routesForm;
    }

    // Convert List<Entity> -> List<DTO>
    public List<RoutesForm> listRoutesForm(List<Routes> routes) {
        List<RoutesForm> routesForms = new ArrayList<>();
        for (Routes route : routes) {
            RoutesForm routesForm = remap(route);
            routesForms.add(routesForm);
        }
        return routesForms;
    }
}


