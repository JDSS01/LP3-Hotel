package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;

    // Nome ou descrição do produto (ex: "Refrigerante Lata 350ml")
    private String descricao;

    // Preço do item
    private BigDecimal valor;

}