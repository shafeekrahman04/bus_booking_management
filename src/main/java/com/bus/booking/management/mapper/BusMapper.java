package com.bus.booking.management.mapper;


import com.bus.booking.management.model.Bus;
import com.bus.booking.management.payload.dto.BusForm;
import com.bus.booking.management.reftype.YNStatus;
import com.bus.booking.management.utils.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("BusMapper")
public class BusMapper {

    public Bus map(BusForm busForm) {
        Bus bus = new Bus();
        bus.setBusName(busForm.getBusName());
        bus.setBusNumber(busForm.getBusNumber());
        bus.setBusType(busForm.getBusType());
        bus.setCapacity(busForm.getCapacity());
        bus.setDeleted(YNStatus.NO.getStatus());
        bus.setCreatedBy(StringUtils.user);
        bus.setCreatedOn(StringUtils.now);
        return bus;
    }

    public BusForm remap(Bus bus) {
        BusForm busForm = new BusForm();
        busForm.setId(String.valueOf(bus.getId()));
        busForm.setBusName(bus.getBusName());
        busForm.setBusNumber(bus.getBusNumber());
        busForm.setBusType(bus.getBusType());
        busForm.setCapacity(bus.getCapacity());
        return busForm;
    }

    public Bus map(BusForm busForm, Bus bus) {
        bus.setBusName(busForm.getBusName());
        bus.setBusNumber(busForm.getBusNumber());
        bus.setBusType(busForm.getBusType());
        bus.setCapacity(busForm.getCapacity());
        bus.setDeleted(YNStatus.NO.getStatus());
        bus.setUpdatedBy(StringUtils.user);
        bus.setUpdatedOn(StringUtils.now);
        return bus;
    }

    public List<BusForm> listBusForm(List<Bus> articles) {
        List<BusForm> busForms = new ArrayList<>();
        for (Bus bus : articles) {
            BusForm busForm = new BusForm();
            busForm.setId(String.valueOf(bus.getId()));
            busForm.setBusName(bus.getBusName());
            busForm.setBusNumber(bus.getBusNumber());
            busForm.setBusType(bus.getBusType());
            busForm.setCapacity(bus.getCapacity());
            busForms.add(busForm);
        }

        return busForms;
    }
}
