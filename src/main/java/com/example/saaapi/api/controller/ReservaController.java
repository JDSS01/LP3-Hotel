package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.ReservaDTO;
import com.example.saaapi.model.entity.Reserva;
import com.example.saaapi.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
@CrossOrigin
public class ReservaController {

    private final ReservaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> get() {
        // Busca todas as reservas cadastradas no banco de dados
        List<Reserva> reservas = service.getReservas();

        // Converte a lista de entidades Reserva para ReservaDTO
        List<ReservaDTO> reservasDTO = reservas.stream()
                .map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(reservasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Reserva> reserva = service.getReservaById(id);

        if (!reserva.isPresent()) {
            return new ResponseEntity<>("Reserva não encontrada", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        ReservaDTO dto = modelMapper.map(reserva.get(), ReservaDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ReservaDTO dto) {
        try {
            // Converte o DTO recebido (JSON do Postman) para a Entidade Reserva
            Reserva reserva = modelMapper.map(dto, Reserva.class);

            // Salva no banco de dados usando o Service.
            // É lá no Service que você vai colocar regras como verificar se o quarto está disponível nas datas solicitadas!
            Reserva reservaSalva = service.salvar(reserva);

            // Converte a reserva salva de volta para DTO para retornar na resposta
            ReservaDTO dtoSalvo = modelMapper.map(reservaSalva, ReservaDTO.class);
            return new ResponseEntity<>(dtoSalvo, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Captura erros de validação (ex: conflito de datas ou quarto indisponível)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}