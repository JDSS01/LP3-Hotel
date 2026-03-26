package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Servicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Double valor;

    // Relacionamento 0..* para 1 com Pedido
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}