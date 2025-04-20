package com.jeanbezerra.performance.simulation.api.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jeanbezerra.performance.simulation.api.entity.CarEntity;
import com.jeanbezerra.performance.simulation.api.respository.CarRepository;
import com.jeanbezerra.performance.simulation.api.service.CarService;

import jakarta.transaction.Transactional;

@Service
public class CarServiceImpl implements CarService {

	private final CarRepository carRepository;

	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
    @Transactional
    public Optional<CarEntity> findByVinLocked(String vin) {
        return carRepository.findByVin(vin);
    }
}