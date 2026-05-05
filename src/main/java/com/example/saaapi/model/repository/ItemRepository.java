package com.example.saaapi.model.repository;

import com.example.saaapi.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // Busca itens que contenham uma determinada descrição (ex: "Água")
    List<Item> findByDescricaoContainingIgnoreCase(String descricao);

    // Busca itens com valor abaixo de um determinado preço
    // Útil para filtros de busca no sistema
    List<Item> findByValorLessThanEqual(java.math.BigDecimal valor);
}