package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Faturamento {


    private Double valorTotal;

    @ManyToOne
    private Quarto quarto;
}