package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.HospedeDTO;
import com.example.saaapi.model.entity.Hospede;
import com.example.saaapi.service.HospedeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hospedes")
@RequiredArgsConstructor
@CrossOrigin
public class HospedeController {

    private final HospedeService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<HospedeDTO>> get() {
        // Busca todos os hóspedes no banco de dados através do Service
        List<Hospede> hospedes = service.getHospedes();

        // Converte a lista de entidades Hospede para HospedeDTO
        List<HospedeDTO> hospedesDTO = hospedes.stream()
                .map(hospede -> modelMapper.map(hospede, HospedeDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(hospedesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Hospede> hospede = service.getHospedeById(id);

        if (!hospede.isPresent()) {
            return new ResponseEntity<>("Hóspede não encontrado", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        HospedeDTO dto = modelMapper.map(hospede.get(), HospedeDTO.class);
        return ResponseEntity.ok(dto);
    }


}