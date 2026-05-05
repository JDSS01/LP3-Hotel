package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.CheckOutDTO;
import com.example.saaapi.model.entity.CheckOut;
import com.example.saaapi.service.CheckOutService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/checkouts")
@RequiredArgsConstructor
@CrossOrigin
public class CheckOutController {

    private final CheckOutService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CheckOutDTO>> get() {
        // Busca todos os registros de check-out no banco
        List<CheckOut> checkouts = service.getCheckOuts();

        // Converte a lista de Entidades para DTOs
        List<CheckOutDTO> checkoutsDTO = checkouts.stream()
                .map(checkout -> modelMapper.map(checkout, CheckOutDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(checkoutsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<CheckOut> checkout = service.getCheckOutById(id);

        if (!checkout.isPresent()) {
            return new ResponseEntity<>("Check-out não encontrado", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        CheckOutDTO dto = modelMapper.map(checkout.get(), CheckOutDTO.class);
        return ResponseEntity.ok(dto);
    }


}