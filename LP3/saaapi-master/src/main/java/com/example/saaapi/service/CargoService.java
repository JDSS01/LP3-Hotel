package com.example.saaapi.service;

import com.example.saaapi.model.entity.Cargo;
import com.example.saaapi.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CargoService {

    // Injeta o repositório para conseguirmos acessar o banco de dados
    @Autowired
    private CargoRepository repository;

    public List<Cargo> getCargos() {
        return repository.findAll();
    }

    public Optional<Cargo> getCargoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cargo salvar(Cargo cargo) {
        validar(cargo);
        return repository.save(cargo);
    }

    @Transactional
    public void excluir(Cargo cargo) {
        Objects.requireNonNull(cargo.getId());
        repository.delete(cargo);
    }

    // Regra de negócio simples para evitar erros no banco
    public void validar(Cargo cargo) {
        if (cargo.getNome() == null || cargo.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("argo é obrigatório");
        }
    }
}