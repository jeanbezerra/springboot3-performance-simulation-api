package com.jeanbezerra.performance.simulation.api.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanbezerra.performance.simulation.api.entity.CarEntity;
import com.jeanbezerra.performance.simulation.api.service.CarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(
        summary = "Buscar carro por VIN (chassi)",
        description = "Retorna os dados do carro correspondente ao VIN fornecido, com lock pessimista para garantir consistência transacional."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carro encontrado com sucesso"),
        @ApiResponse(responseCode = "204", description = "Carro não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{vin}")
    public ResponseEntity<?> getCarByVin(
            @Parameter(description = "VIN (número do chassi) do carro", example = "9BWZZZ377VT004251")
            @PathVariable String vin) {
        try {
            Optional<CarEntity> carOpt = carService.findByVinLocked(vin);

            if (carOpt.isPresent()) {
                return ResponseEntity.ok(carOpt.get()); // 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro com VIN '" + vin + "' não encontrado."); // 204
            }

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar carro: " + ex.getMessage()); // 500
        }
    }
}