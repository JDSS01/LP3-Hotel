package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDTO {

    private Long id;

    // Nome ou descrição do serviço (ex: "Lavanderia - Peça avulsa", "Massagem Relaxante")
    private String descricao;

    // Valor cobrado pela execução do serviço
    private BigDecimal valor;

}