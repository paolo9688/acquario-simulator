package com.example.acquario_simulator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "acquario")
public class Acquario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "capacità-max-pesci")
    private Integer capacitaMax;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "livello-pulizia")
    private Long livelloPulizia;

    @NotNull
    @Column(name = "temperatura-acqua")
    private Double temperaturaAcqua;

    public Acquario() {}

    public Acquario(Long id, Integer capacitaMax, Long livelloPulizia, Double temperaturaAcqua) {
        this.id = id;
        this.capacitaMax = capacitaMax;
        this.livelloPulizia = livelloPulizia;
        this.temperaturaAcqua = temperaturaAcqua;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacitaMax() {
        return capacitaMax;
    }

    public void setCapacitaMax(Integer capacitaMax) {
        this.capacitaMax = capacitaMax;
    }

    public Long getLivelloPulizia() {
        return livelloPulizia;
    }

    public void setLivelloPulizia(Long livelloPulizia) {
        this.livelloPulizia = livelloPulizia;
    }

    public Double getTemperaturaAcqua() {
        return temperaturaAcqua;
    }

    public void setTemperaturaAcqua(Double temperaturaAcqua) {
        this.temperaturaAcqua = temperaturaAcqua;
    }

    @Override
    public String toString() {
        return "Acquario{" +
                "id=" + id +
                ", capacitaMax=" + capacitaMax +
                ", livelloPulizia=" + livelloPulizia +
                ", temperaturaAcqua=" + temperaturaAcqua +
                '}';
    }
}
