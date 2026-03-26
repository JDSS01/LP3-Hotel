package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pedido {


    @ManyToOne
    private Quarto quarto;


}