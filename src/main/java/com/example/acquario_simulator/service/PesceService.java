package com.example.acquario_simulator.service;

import com.example.acquario_simulator.entity.Acquario;
import com.example.acquario_simulator.entity.Pesce;
import com.example.acquario_simulator.repository.PesceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PesceService {

    @Autowired
    private PesceRepository pesceRepository;

    private Acquario acquario;

    // Aggiungi un nuovo pesce:
    public Pesce createPesce(Pesce pesce) {
        return pesceRepository.save(pesce);
    }

    // Ottieni tutti i pesci:
    public List<Pesce> getPesci() {
        List<Pesce> listaPesci = pesceRepository.findAll();
        return listaPesci;
    }

    // Cancella un pesce esistente:
    public Optional<Pesce> deletePesce(Long id) {
        Optional<Pesce> pesceOptional = pesceRepository.findById(id);

        if (pesceOptional.isPresent()) {
            pesceRepository.deleteById(id);
            return pesceOptional;
        } else {
            return Optional.empty();
        }
    }

    // Modifica un pesce esistente:
    public Optional<Pesce> updatePesce(Long id, Pesce pesceDetails) {
        Optional<Pesce> pesceOptional = pesceRepository.findById(id);

        if (pesceOptional.isPresent()) {
            pesceOptional.get().setNome(pesceDetails.getNome());
            pesceOptional.get().setSpecie(pesceDetails.getSpecie());
            pesceOptional.get().setLivelloFame(pesceDetails.getLivelloFame());
            pesceOptional.get().setLivelloSalute(pesceDetails.getLivelloSalute());
            pesceOptional.get().setEta(pesceDetails.getEta());
            Pesce pesce = pesceRepository.save(pesceOptional.get());
            return Optional.of(pesce);
        } else {
            return Optional.empty();
        }
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
