package com.bus.booking.management.service.bus;

import com.bus.booking.management.dao.BusRepository;
import com.bus.booking.management.model.Bus;
import com.bus.booking.management.reftype.YNStatus;
import com.bus.booking.management.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Override
    public List<Bus> listAllBus() {
        return busRepository.findAllByDeleted(YNStatus.NO.getStatus());
    }

    @Override
    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    @Override
    public Bus updateBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Bus deleteBus(Long id) {
        Optional<Bus> busOptional = busRepository.findById(id);
        if (busOptional.isPresent()) {
            Bus bus = busOptional.get();
            bus.setUpdatedBy(StringUtils.user);
            bus.setUpdatedOn(StringUtils.now);
            bus.setDeleted(YNStatus.YES.getStatus());
            return bus;
        }
        return null;
    }
}
