package com.example.saaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

    // Dados herdados de Pessoa
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;

    // Dados específicos de Funcionario
    private String matricula;

    // ID do cargo associado a este funcionário (ex: 1 para Recepcionista)
    private Long cargoId;

}