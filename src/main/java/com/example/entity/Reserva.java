package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Reserva {

    @ManyToOne
    @JoinColumn(name = "hospede_id")
    private Hospede hospede;

    // Relacionamento 1 para 1..* com Quarto
    @OneToMany(mappedBy = "reserva")
    private List<Quarto> quartos;
}