package com.example.acquario_simulator.controller;

import com.example.acquario_simulator.entity.Acquario;
import com.example.acquario_simulator.entity.Pesce;
import com.example.acquario_simulator.service.AcquarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/acquario")
public class AcquarioController {

    @Autowired
    private AcquarioService acquarioService;

    // Aggiungi un nuovo acquario:
    @PostMapping("/create-acquario")
    public ResponseEntity<Acquario> createPesce(@RequestBody Acquario acquario) {
        Acquario newAcquario = acquarioService.createAcquario(acquario);
        return ResponseEntity.ok(newAcquario);
    }

    // Trova un acquario per id:
    @GetMapping("/find-acquario/{id}")
    public ResponseEntity<Optional<Acquario>> getAcquarioById(@PathVariable Long id) {
        Optional<Acquario> acquarioOptional = acquarioService.getAcquarioById(id);

        if (acquarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(acquarioOptional);
    }

    // Modifica l'acquario:
    @PutMapping("/update-acquario/{id}")
    public ResponseEntity<Optional<Acquario>> updatePesce(@PathVariable Long id, @RequestBody Acquario acquarioDetails) {
        Optional<Acquario> acquarioToUpdate = acquarioService.updateAcquario(id, acquarioDetails);

        if (acquarioToUpdate.isPresent()) {
            return ResponseEntity.ok(acquarioToUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    // Pulizia acquario:
    @PutMapping("/pulizia-acquario/{id}")
    public ResponseEntity<Optional<Acquario>> puliziaAcquario(@PathVariable Long id, @RequestParam Long livelloPulizia) {
        Optional<Acquario> acquarioToClean = acquarioService.puliziaAcquario(id, livelloPulizia);

        if (acquarioToClean.isPresent()) {
            return ResponseEntity.ok(acquarioToClean);
        }
        return ResponseEntity.notFound().build();
    }
}
