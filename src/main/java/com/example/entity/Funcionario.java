package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Funcionario extends Pessoa {
    private String matricula;

    @ManyToOne
    private Cargo cargo;
}