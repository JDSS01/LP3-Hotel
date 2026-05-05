package com.example.saaapi.model.repository;

import com.example.saaapi.model.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Exemplo de busca personalizada: encontrar funcionário pela matrícula
    Optional<Funcionario> findByMatricula(String matricula);

    // Você também pode buscar por campos herdados de Pessoa
    Optional<Funcionario> findByCpf(String cpf);
}