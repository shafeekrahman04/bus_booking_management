package com.bus.booking.management.service.routes;

import com.bus.booking.management.model.Routes;

import java.util.List;

public interface RoutesService {
    public List<Routes> listAllRoutes();

    Routes saveRoutes(Routes routes);

}
