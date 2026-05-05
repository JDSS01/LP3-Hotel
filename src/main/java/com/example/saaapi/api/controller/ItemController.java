package com.example.saaapi.api.controller;

import com.example.saaapi.api.dto.ItemDTO;
import com.example.saaapi.model.entity.Item;
import com.example.saaapi.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/itens") // Rota no plural correto
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    private final ItemService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> get() {
        // Busca todos os itens cadastrados no banco de dados (ex: Refrigerante, Salgadinho)
        List<Item> itens = service.getItens();

        // Converte a lista de entidades Item para ItemDTO
        List<ItemDTO> itensDTO = itens.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(itensDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Item> item = service.getItemById(id);

        if (!item.isPresent()) {
            return new ResponseEntity<>("Item não encontrado", HttpStatus.NOT_FOUND);
        }

        // Converte a entidade encontrada para DTO
        ItemDTO dto = modelMapper.map(item.get(), ItemDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ItemDTO dto) {
        try {
            // Converte o DTO recebido no corpo da requisição para a Entidade Item
            Item item = modelMapper.map(dto, Item.class);

            // Salva no banco de dados usando o Service
            Item itemSalvo = service.salvar(item);

            // Converte o item salvo de volta para DTO para retornar na resposta
            ItemDTO dtoSalvo = modelMapper.map(itemSalvo, ItemDTO.class);
            return new ResponseEntity<>(dtoSalvo, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Captura erros de validação (ex: tentar salvar um item sem preço ou sem descrição)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}