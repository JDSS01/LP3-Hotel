package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.PessoaDTO;
import com.example.saaapi.model.entity.Pessoa;
import com.example.saaapi.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pessoas")
@RequiredArgsConstructor
@CrossOrigin
public class PessoaController {

    private final PessoaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> get() {
        // Busca todas as pessoas no banco de dados (incluindo Hóspedes e Funcionários)
        List<Pessoa> pessoas = service.getPessoas();

        // Converte a lista de entidades Pessoa para PessoaDTO
        List<PessoaDTO> pessoasDTO = pessoas.stream()
                .map(pessoa -> modelMapper.map(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pessoasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = service.getPessoaById(id);

        if (!pessoa.isPresent()) {
            return new ResponseEntity<>("Pessoa não encontrada", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        PessoaDTO dto = modelMapper.map(pessoa.get(), PessoaDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody PessoaDTO dto) {
        try {
            // Converte o DTO recebido para a Entidade Pessoa
            Pessoa pessoa = modelMapper.map(dto, Pessoa.class);

            // Salva no banco de dados usando o Service
            Pessoa pessoaSalva = service.salvar(pessoa);

            // Converte a pessoa salva de volta para DTO para retornar na resposta
            PessoaDTO dtoSalvo = modelMapper.map(pessoaSalva, PessoaDTO.class);
            return new ResponseEntity<>(dtoSalvo, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Captura erros de validação (ex: CPF inválido ou em branco)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}