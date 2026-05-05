package com.example.saaapi.model.repository;

import com.example.saaapi.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    // Busca serviços por parte da descrição (ex: "Lavanderia")
    List<Servico> findByDescricaoContainingIgnoreCase(String descricao);

    // Busca serviços com valor dentro de uma faixa específica
    List<Servico> findByValorBetween(java.math.BigDecimal valorInicial, java.math.BigDecimal valorFinal);
}