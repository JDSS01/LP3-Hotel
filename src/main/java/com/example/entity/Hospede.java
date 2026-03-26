package com.example.saaapi.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Hospede extends Pessoa {

    @OneToMany
    private List<Reserva> reservas;

}