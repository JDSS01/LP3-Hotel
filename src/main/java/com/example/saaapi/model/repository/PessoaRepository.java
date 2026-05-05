package com.example.saaapi.model.repository;

import com.example.saaapi.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    // Busca qualquer pessoa (Hóspede ou Funcionário) pelo CPF
    // Útil para evitar cadastros duplicados no sistema todo
    Optional<Pessoa> findByCpf(String cpf);

    // Busca pessoas pelo nome exato ou parte dele
    java.util.List<Pessoa> findByNomeContainingIgnoreCase(String nome);
}