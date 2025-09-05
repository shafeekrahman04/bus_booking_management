package com.bus.booking.management.service.routes;

import com.bus.booking.management.dao.RoutesRepository;
import com.bus.booking.management.model.Routes;
import com.bus.booking.management.reftype.YNStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoutesServiceImpl implements RoutesService {

    @Autowired
    private RoutesRepository routesRepository;

    @Override
    public List<Routes> listAllRoutes() {
        return routesRepository.findAllByDeleted(YNStatus.NO.getStatus());
    }

    @Override
    public Routes saveRoutes(Routes routes) {
        return routesRepository.save(routes);
    }

    @Override
    public Optional<Routes> getRoutesById(Long id) {
        return routesRepository.findById(id);
    }

    @Override
    public Routes updateRoutes(Routes routes) {
        return routesRepository.save(routes);
    }
}
