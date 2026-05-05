package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;

    // Nome completo da pessoa
    private String nome;

    // CPF (idealmente sem formatação no banco, apenas os números)
    private String cpf;

    // Telefone de contato
    private String telefone;

}