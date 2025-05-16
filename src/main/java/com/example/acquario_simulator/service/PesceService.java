package com.example.acquario_simulator.service;

import com.example.acquario_simulator.entity.Acquario;
import com.example.acquario_simulator.entity.Pesce;
import com.example.acquario_simulator.repository.PesceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesceService {

    @Autowired
    private PesceRepository pesceRepository;

    private Acquario acquario;

    // Aggiungi un nuovo pesce:
    public Pesce createPesce(Pesce pesce) {
        return pesceRepository.save(pesce);
    }

    // Nutrire i pesci:
    public List<Pesce> nutriPesci() {
        // Trovo tutti i pesci dell'acquario:
        List<Pesce> listaPesci = pesceRepository.findAll();

        // Ciclo tutti i pesci e ad ognuno aggiungo 50 al livello di fame:
        for (Pesce pesce : listaPesci) {
            pesce.setLivelloFame(pesce.getLivelloFame() + 50);
        }

        // Diminuisco di 15 il livello di pulizia dell'acquario:
        acquario.setLivelloPulizia(acquario.getLivelloPulizia() - 15);

        return listaPesci;
    }
}
