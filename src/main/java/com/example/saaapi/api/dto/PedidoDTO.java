package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;

    // Data e hora em que o pedido foi realizado
    private LocalDateTime dataHora;

    // O valor total pode vir preenchido ou ser calculado lá no PedidoService somando os itens!
    private BigDecimal valorTotal;

    // ID da reserva para colocar a despesa na conta do hóspede
    private Long reservaId;

    // Lista com os IDs dos itens consumidos (ex: [1, 3, 5] para uma água, um refri e um lanche)
    private List<Long> itensIds;

}