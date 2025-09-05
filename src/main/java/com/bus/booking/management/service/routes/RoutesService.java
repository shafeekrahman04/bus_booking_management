package com.bus.booking.management.service.routes;

import com.bus.booking.management.model.Routes;

import java.util.List;
import java.util.Optional;

public interface RoutesService {
    public List<Routes> listAllRoutes();

    Routes saveRoutes(Routes routes);

    public Optional<Routes> getRoutesById(Long id);

    public Routes updateRoutes(Routes routes);
}
