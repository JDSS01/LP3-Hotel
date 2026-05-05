package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.PedidoDTO;
import com.example.saaapi.model.entity.Pedido;
import com.example.saaapi.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
@CrossOrigin
public class PedidoController {

    private final PedidoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> get() {
        // Busca todos os pedidos no banco de dados (vinculados às reservas)
        List<Pedido> pedidos = service.getPedidos();

        // Converte a lista de entidades Pedido para PedidoDTO
        List<PedidoDTO> pedidosDTO = pedidos.stream()
                .map(pedido -> modelMapper.map(pedido, PedidoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Pedido> pedido = service.getPedidoById(id);

        if (!pedido.isPresent()) {
            return new ResponseEntity<>("Pedido não encontrado", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        PedidoDTO dto = modelMapper.map(pedido.get(), PedidoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody PedidoDTO dto) {
        try {
            // Converte o DTO recebido (JSON do Postman) para a Entidade Pedido
            Pedido pedido = modelMapper.map(dto, Pedido.class);

            // Salva no banco de dados usando o Service (onde a lógica de soma de valores dos itens deve ficar)
            Pedido pedidoSalvo = service.salvar(pedido);

            // Converte o pedido salvo de volta para DTO para retornar na resposta
            PedidoDTO dtoSalvo = modelMapper.map(pedidoSalvo, PedidoDTO.class);
            return new ResponseEntity<>(dtoSalvo, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Captura erros de validação (ex: tentar salvar um pedido sem vincular a uma reserva)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}