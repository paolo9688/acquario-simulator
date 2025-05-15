package com.example.acquario_simulator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pesci")
public class Pesce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "specie")
    private String specie;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "livello-fame")
    private Integer livelloFame;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "livello-salute")
    private Integer livelloSalute;

    @NotNull
    @Column(name = "età")
    private Integer eta;

    public Pesce() {}

    public Pesce(Long id, String nome, String specie, Integer livelloFame, Integer livelloSalute, Integer eta) {
        this.id = id;
        this.nome = nome;
        this.specie = specie;
        this.livelloFame = livelloFame;
        this.livelloSalute = livelloSalute;
        this.eta = eta;
    }

    public Pesce(String nome, String specie, Integer livelloFame, Integer livelloSalute, Integer eta) {
        this.nome = nome;
        this.specie = specie;
        this.livelloFame = livelloFame;
        this.livelloSalute = livelloSalute;
        this.eta = eta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public Integer getLivelloFame() {
        return livelloFame;
    }

    public void setLivelloFame(Integer livelloFame) {
        this.livelloFame = livelloFame;
    }

    public Integer getLivelloSalute() {
        return livelloSalute;
    }

    public void setLivelloSalute(Integer livelloSalute) {
        this.livelloSalute = livelloSalute;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    @Override
    public String toString() {
        return "Pesce{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", specie='" + specie + '\'' +
                ", livelloFame=" + livelloFame +
                ", livelloSalute=" + livelloSalute +
                ", eta=" + eta +
                '}';
    }
}
