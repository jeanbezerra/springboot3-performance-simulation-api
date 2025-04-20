package com.jeanbezerra.performance.simulation.api.respository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.jeanbezerra.performance.simulation.api.entity.CarEntity;

import jakarta.persistence.LockModeType;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<CarEntity> findByVin(String vin);

}