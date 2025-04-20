package com.jeanbezerra.performance.simulation.api.service;

import java.util.Optional;

import com.jeanbezerra.performance.simulation.api.entity.CarEntity;

public interface CarService {

	Optional<CarEntity> findByVinLocked(String vin);
}
