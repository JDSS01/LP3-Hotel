package com.example.saaapi.model.repository;

import com.example.saaapi.model.entity.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {

    // Busca um hóspede pelo CPF (atributo herdado de Pessoa)
    Optional<Hospede> findByCpf(String cpf);

    // Busca hóspedes que moram em um determinado endereço/cidade
    // Útil para relatórios ou filtros de marketing do hotel
    java.util.List<Hospede> findByEnderecoContaining(String parteDoEndereco);
}