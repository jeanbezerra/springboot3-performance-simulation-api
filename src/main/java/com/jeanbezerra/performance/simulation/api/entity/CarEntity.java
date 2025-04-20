package com.jeanbezerra.performance.simulation.api.entity;

import java.time.Year;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cars")
public class CarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false, unique = false, updatable = false)
	private UUID id;

	@Size(min = 17, max = 17)
	@Column(nullable = false, length = 17, unique = true)
	private String vin; // Número do chassi

	@Column(nullable = false)
	private String brand; // Marca

	@Column(nullable = false)
	private String model; // Modelo

	@Column(nullable = false)
	private Year year; // Ano de fabricação

	@Column(nullable = false)
	private String color;

	@Column(nullable = false)
	private String fuelType;

	@Column(nullable = false)
	private String transmission; // Manual ou automática

	@Column(nullable = false)
	private Integer mileage; // Quilometragem

	@Column(nullable = false)
	private String plateNumber; // Placa

	public CarEntity() {
	}

	public CarEntity(UUID id, String vin, String brand, String model, Year year, String color, String fuelType,
			String transmission, Integer mileage, String plateNumber) {
		super();
		this.id = id;
		this.vin = vin;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.color = color;
		this.fuelType = fuelType;
		this.transmission = transmission;
		this.mileage = mileage;
		this.plateNumber = plateNumber;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

}
