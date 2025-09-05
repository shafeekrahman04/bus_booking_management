package com.bus.booking.management.service.bus;

import com.bus.booking.management.model.Bus;

import java.util.List;
import java.util.Optional;

public interface BusService {

    public List<Bus> listAllBus();

    public Bus saveBus(Bus bus);

    public Optional<Bus> getBusById(Long id);

    public Bus updateBus(Bus bus);

    public Bus deleteBus(Long id);
}
