package com.example.saaapi.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Table;
// Estes são os que estão faltando:
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED) // O erro aponta para cá
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}