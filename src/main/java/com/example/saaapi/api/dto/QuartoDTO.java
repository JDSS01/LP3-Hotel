package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoDTO {

    private Long id;

    // Número de identificação física do quarto (ex: 101, 205)
    private Integer numero;

    // Categoria ou tipo do quarto (ex: "Suíte Master", "Quarto Standard", "Casal")
    private String tipo;

    // Quantidade máxima de pessoas que o quarto comporta
    private Integer capacidade;

    // Valor cobrado por noite de estadia
    private BigDecimal valorDiaria;

}