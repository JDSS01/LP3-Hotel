package com.example.saaapi.model.repository;

import com.example.saaapi.model.entity.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut, Long> {

    // Exemplo de busca personalizada: encontrar checkout por ID da reserva
    // Optional<CheckOut> findByReservaId(Long reservaId);
}