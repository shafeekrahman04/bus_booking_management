package com.bus.booking.management.mapper;

import com.bus.booking.management.dao.BusRepository;
import com.bus.booking.management.model.Bus;
import com.bus.booking.management.model.Routes;
import com.bus.booking.management.payload.dto.RoutesForm;
import com.bus.booking.management.reftype.YNStatus;
import com.bus.booking.management.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("RouteMapper")
public class RoutesMapper {

    @Autowired
    private BusRepository busRepository;

    // Convert Entity -> DTO
    public RoutesForm remap(Routes route) {
        RoutesForm routesForm = new RoutesForm();
        routesForm.setId(String.valueOf(route.getId()));
        if (route.getBus() != null) {
            routesForm.setBusId(String.valueOf(route.getBus().getId()));
            routesForm.setBusNumber(route.getBus().getBusNumber());
        }
        routesForm.setPricePerSeat(route.getPricePerSeat() != null ? route.getPricePerSeat().toString() : null);
        routesForm.setOrigin(route.getOrigin());
        routesForm.setDestination(route.getDestination());
        routesForm.setDistance(route.getDistance());
        routesForm.setStatus(route.getStatus());
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

    public Routes map(RoutesForm routesForm) {
        Routes route = new Routes();
        if (routesForm.getBusId() != null) {
            Bus bus = busRepository.findById(Long.valueOf(routesForm.getBusId()))
                    .orElse(null);
            route.setBus(bus);
        }
        route.setPricePerSeat(routesForm.getPricePerSeat() != null ? new BigDecimal(routesForm.getPricePerSeat()) : null);
        route.setOrigin(routesForm.getOrigin());
        route.setDestination(routesForm.getDestination());
        route.setDistance(routesForm.getDistance());
        route.setDate(routesForm.getDate() != null ? LocalDate.parse(routesForm.getDate()) : null);
        route.setPickupTime(routesForm.getPickupTime() != null ? LocalTime.parse(routesForm.getPickupTime()) : null);
        route.setDropTime(routesForm.getDropTime() != null ? LocalTime.parse(routesForm.getDropTime()) : null);
        route.setStatus(routesForm.getStatus());
        route.setDeleted(YNStatus.NO.getStatus());
        route.setCreatedBy(StringUtils.user);
        route.setCreatedOn(StringUtils.now);
        return route;
    }

    public Routes map(RoutesForm routesForm, Routes route) {
        if (routesForm.getBusId() != null) {
            Bus bus = busRepository.findById(Long.valueOf(routesForm.getBusId()))
                    .orElse(null);
            route.setBus(bus);
        }
        route.setPricePerSeat(new BigDecimal(routesForm.getPricePerSeat()));
        route.setOrigin(routesForm.getOrigin());
        route.setDestination(routesForm.getDestination());
        route.setDistance(routesForm.getDistance());
        route.setDate(LocalDate.parse(routesForm.getDate()));
        route.setPickupTime(LocalTime.parse(routesForm.getPickupTime()));
        route.setDropTime(LocalTime.parse(routesForm.getDropTime()));
        route.setStatus(routesForm.getStatus());
        route.setDeleted(YNStatus.NO.getStatus());
        route.setUpdatedBy(StringUtils.user);
        route.setUpdatedOn(StringUtils.now);
        return route;
    }


}


