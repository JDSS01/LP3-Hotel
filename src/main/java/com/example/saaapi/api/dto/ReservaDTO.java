package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Long id;

    // Data prevista para a chegada do hóspede
    private LocalDate dataEntrada;

    // Data prevista para a saída do hóspede
    private LocalDate dataSaida;

    // Status da reserva (ex: "CONFIRMADA", "CANCELADA", "FINALIZADA")
    private String status;

    // Valor total previsto da hospedagem (pode ser calculado multiplicando as diárias do quarto)
    private BigDecimal valorTotal;

    // ID do hóspede que está fazendo a reserva
    private Long hospedeId;

    // ID do quarto que foi escolhido
    private Long quartoId;

}