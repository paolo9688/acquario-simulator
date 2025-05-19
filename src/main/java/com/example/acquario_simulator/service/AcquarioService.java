package com.example.acquario_simulator.service;

import com.example.acquario_simulator.entity.Acquario;
import com.example.acquario_simulator.entity.Pesce;
import com.example.acquario_simulator.repository.AcquarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcquarioService {

    @Autowired
    private AcquarioRepository acquarioRepository;

    // Aggiungi un nuovo acquario:
    public Acquario createAcquario(Acquario acquario) {
        return acquarioRepository.save(acquario);
    }

    // Trova un acquario per id:
    public Optional<Acquario> getAcquarioById(Long id) {
        Optional<Acquario> acquarioOptional = acquarioRepository.findById(id);
        return acquarioOptional;
    }

    // Modifica l'acquario:
    public Optional<Acquario> updateAcquario(Long id, Acquario acquarioDetails) {
        Optional<Acquario> acquarioOptional = acquarioRepository.findById(id);

        if (acquarioOptional.isPresent()) {
            acquarioOptional.get().setCapacitaMax(acquarioDetails.getCapacitaMax());
            acquarioOptional.get().setLivelloPulizia(acquarioDetails.getLivelloPulizia());
            acquarioOptional.get().setTemperaturaAcqua(acquarioDetails.getTemperaturaAcqua());
            Acquario acquario = acquarioRepository.save(acquarioOptional.get());
            return Optional.of(acquario);
        } else {
            return Optional.empty();
        }
    }
}
