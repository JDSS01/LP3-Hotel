package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckInDTO {

    private Long id;

    // Data e hora exata em que o hóspede chegou ao hotel
    private LocalDateTime dataHora;

    // ID da reserva correspondente a este check-in
    private Long reservaId;

    // ID do funcionário (recepcionista) que realizou o atendimento
    private Long recepcionistaId;

}