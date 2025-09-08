package com.bus.booking.management.dao;

import com.bus.booking.management.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoutesRepository extends JpaRepository<Routes, Long> {
    List<Routes> findAllByDeleted(String deleted);

    long countByStatus(String status);

    List<Routes> findByOriginAndDestinationAndDate(String origin, String destination, LocalDate date);

}
