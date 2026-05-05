package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.ServicoDTO;
import com.example.saaapi.model.entity.Servico;
import com.example.saaapi.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/servicos")
@RequiredArgsConstructor
@CrossOrigin
public class ServicoController {

    private final ServicoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> get() {
        // Busca todos os serviços cadastrados no banco de dados
        List<Servico> servicos = service.getServicos();

        // Converte a lista de entidades Servico para ServicoDTO
        List<ServicoDTO> servicosDTO = servicos.stream()
                .map(servico -> modelMapper.map(servico, ServicoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(servicosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Servico> servico = service.getServicoById(id);

        if (!servico.isPresent()) {
            return new ResponseEntity<>("Serviço não encontrado", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        ServicoDTO dto = modelMapper.map(servico.get(), ServicoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ServicoDTO dto) {
        try {
            // Converte o DTO recebido (JSON do Postman) para a Entidade Servico
            Servico servico = modelMapper.map(dto, Servico.class);

            // Salva no banco de dados usando o Service
            Servico servicoSalvo = service.salvar(servico);

            // Converte o serviço salvo de volta para DTO para retornar na resposta
            ServicoDTO dtoSalvo = modelMapper.map(servicoSalvo, ServicoDTO.class);
            return new ResponseEntity<>(dtoSalvo, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Captura erros de validação (ex: tentar cadastrar um serviço sem descrição ou sem valor)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}