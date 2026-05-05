package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.QuartoDTO;
import com.example.saaapi.model.entity.Quarto;
import com.example.saaapi.service.QuartoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/quartos")
@RequiredArgsConstructor
@CrossOrigin
public class QuartoController {

    private final QuartoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<QuartoDTO>> get() {
        // Busca todos os quartos cadastrados no banco de dados
        List<Quarto> quartos = service.getQuartos();

        // Converte a lista de entidades Quarto para QuartoDTO
        List<QuartoDTO> quartosDTO = quartos.stream()
                .map(quarto -> modelMapper.map(quarto, QuartoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(quartosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Quarto> quarto = service.getQuartoById(id);

        if (!quarto.isPresent()) {
            return new ResponseEntity<>("Quarto não encontrado", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        QuartoDTO dto = modelMapper.map(quarto.get(), QuartoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody QuartoDTO dto) {
        try {
            // Converte o DTO recebido (JSON do Postman) para a Entidade Quarto
            Quarto quarto = modelMapper.map(dto, Quarto.class);

            // Salva no banco de dados usando o Service
            Quarto quartoSalvo = service.salvar(quarto);

            // Converte o quarto salvo de volta para DTO para retornar na resposta
            QuartoDTO dtoSalvo = modelMapper.map(quartoSalvo, QuartoDTO.class);
            return new ResponseEntity<>(dtoSalvo, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Captura erros de validação (ex: número do quarto já cadastrado ou valor inválido)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}