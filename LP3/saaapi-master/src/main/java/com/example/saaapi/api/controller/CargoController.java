package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.CargoDTO;
import com.example.saaapi.model.entity.Cargo;
import com.example.saaapi.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cargos") // Rota ajustada para cargos
@RequiredArgsConstructor
@CrossOrigin
public class CargoController {

    // O RequiredArgsConstructor do Lombok injeta essas dependências automaticamente
    private final CargoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CargoDTO>> get() {
        // Busca os cargos usando o método do CargoService
        List<Cargo> cargos = service.getCargos();

        // Converte a lista de Entidades para uma lista de DTOs
        List<CargoDTO> cargosDTO = cargos.stream()
                .map(cargo -> modelMapper.map(cargo, CargoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cargosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Cargo> cargo = service.getCargoById(id);

        if (!cargo.isPresent()) {
            return new ResponseEntity<>("Cargo não encontrado", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        CargoDTO dto = modelMapper.map(cargo.get(), CargoDTO.class);
        return ResponseEntity.ok(dto);
    }


}