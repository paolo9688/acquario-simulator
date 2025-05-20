package com.example.acquario_simulator.entity;

import java.util.List;

public class MostraParametri {
    private Long livelloPulizia;
    private Double temperaturaAcqua;
    private List<Pesce> listaPesci;

    public MostraParametri() {}

    public MostraParametri(Long livelloPulizia, Double temperaturaAcqua, List<Pesce> listaPesci) {
        this.livelloPulizia = livelloPulizia;
        this.temperaturaAcqua = temperaturaAcqua;
        this.listaPesci = listaPesci;
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

    public List<Pesce> getListaPesci() {
        return listaPesci;
    }

    public void setListaPesci(List<Pesce> listaPesci) {
        this.listaPesci = listaPesci;
    }
}
