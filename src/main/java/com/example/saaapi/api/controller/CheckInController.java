package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.CheckInDTO;
import com.example.saaapi.model.entity.CheckIn;
import com.example.saaapi.service.CheckInService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/checkins")
@RequiredArgsConstructor
@CrossOrigin
public class CheckInController {

    private final CheckInService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CheckInDTO>> get() {
        // Busca todos os registros de check-in no banco
        List<CheckIn> checkins = service.getCheckIns();

        // Converte a lista de Entidades para DTOs
        List<CheckInDTO> checkinsDTO = checkins.stream()
                .map(checkin -> modelMapper.map(checkin, CheckInDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(checkinsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<CheckIn> checkin = service.getCheckInById(id);

        if (!checkin.isPresent()) {
            return new ResponseEntity<>("Check-in não encontrado", HttpStatus.NOT_FOUND);
        }

        CheckInDTO dto = modelMapper.map(checkin.get(), CheckInDTO.class);
        return ResponseEntity.ok(dto);
    }


}