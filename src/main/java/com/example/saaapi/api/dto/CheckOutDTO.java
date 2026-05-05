package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutDTO {

    private Long id;

    // Data e hora exata em que o hóspede encerrou a estadia e deixou o hotel
    private LocalDateTime dataHora;

    // ID da reserva que está sendo finalizada
    private Long reservaId;

    // ID do funcionário (recepcionista) que realizou o procedimento de check-out
    private Long recepcionistaId;

}