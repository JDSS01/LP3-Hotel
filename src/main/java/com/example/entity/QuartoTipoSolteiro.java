package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SOLTEIRO")
public class QuartoTipoSolteiro extends Quarto {
    // Atributos específicos se houver
}

// --- Arquivo QuartoTipoCasal.java ---
@Entity
@DiscriminatorValue("CASAL")
public class QuartoTipoCasal extends Quarto {
    // Atributos específicos se houver
}

// --- Arquivo QuartoTipoSuite.java ---
@Entity
@DiscriminatorValue("SUITE")
public class QuartoTipoSuite extends Quarto {
    // Atributos específicos se houver
}