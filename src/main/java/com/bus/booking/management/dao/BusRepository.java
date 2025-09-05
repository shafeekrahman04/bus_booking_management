package com.bus.booking.management.dao;

import com.bus.booking.management.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findAllByDeleted(String deleted);

}
