package com.example.saaapi.model.repository;

import com.example.saaapi.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Busca todos os pedidos associados a uma reserva específica
    // Essencial para somar o consumo no momento do Check-out
    List<Pedido> findByReservaId(Long reservaId);

}