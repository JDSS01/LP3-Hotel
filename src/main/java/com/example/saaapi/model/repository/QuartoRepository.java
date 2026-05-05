package com.example.saaapi.model.repository;

import com.example.saaapi.model.entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    // Busca um quarto pelo seu número único
    Optional<Quarto> findByNumero(Integer numero);

    // Lista quartos por tipo (ex: "Luxo", "Simples")
    List<Quarto> findByTipoIgnoreCase(String tipo);

    // Busca quartos que comportem uma quantidade mínima de pessoas
    List<Quarto> findByCapacidadeGreaterThanEqual(Integer capacidade);
}
