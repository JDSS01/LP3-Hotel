package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.FuncionarioDTO;
import com.example.saaapi.model.entity.Funcionario;
import com.example.saaapi.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor
@CrossOrigin
public class FuncionarioController {

    private final FuncionarioService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> get() {
        // Busca todos os funcionários através do Service
        List<Funcionario> funcionarios = service.getFuncionarios();

        // Converte a lista de entidades Funcionario para FuncionarioDTO
        List<FuncionarioDTO> funcionariosDTO = funcionarios.stream()
                .map(funcionario -> modelMapper.map(funcionario, FuncionarioDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(funcionariosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);

        if (!funcionario.isPresent()) {
            return new ResponseEntity<>("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        FuncionarioDTO dto = modelMapper.map(funcionario.get(), FuncionarioDTO.class);
        return ResponseEntity.ok(dto);
    }


}