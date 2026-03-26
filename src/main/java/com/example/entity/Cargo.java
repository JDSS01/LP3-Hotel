package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cargo {

    private String nome;


    @OneToMany
    private List<Funcionario> funcionarios;
}