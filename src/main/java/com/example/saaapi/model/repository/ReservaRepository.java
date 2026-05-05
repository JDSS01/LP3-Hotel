package com.example.saaapi.model.repository;

import com.example.saaapi.model.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Busca reservas por status (ex: "PENDENTE", "CONFIRMADA", "CANCELADA")
    List<Reserva> findByStatus(String status);

    // Busca todas as reservas de um hóspede específico
    List<Reserva> findByHospedeId(Long hospedeId);

    // Busca reservas que iniciam em uma data específica
    List<Reserva> findByDataEntrada(LocalDate dataEntrada);

    // Útil para verificar disponibilidade: busca reservas em um período
    List<Reserva> findByDataEntradaBetween(LocalDate inicio, LocalDate fim);
}