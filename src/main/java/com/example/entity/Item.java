package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Item {


    private String nome;
    private Double preco;


    @ManyToOne
    private Pedido pedido;
}