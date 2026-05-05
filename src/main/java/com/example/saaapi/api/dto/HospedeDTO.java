package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospedeDTO {

    // Dados herdados de Pessoa
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;

    // Dados específicos de Hospede
    private String endereco;

}