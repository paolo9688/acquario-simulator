package com.example.acquario_simulator.controller;

import com.example.acquario_simulator.entity.Pesce;
import com.example.acquario_simulator.service.PesceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pesci")
public class PesceController {

    @Autowired
    private PesceService pesceService;

    // Aggiungi un nuovo pesce:
    @PostMapping("/create-pesce")
    public ResponseEntity<Pesce> createPesce(@RequestBody Pesce pesce) {
        Pesce pesceToAdd = pesceService.createPesce(pesce);
        return ResponseEntity.ok(pesceToAdd);
    }

    // Cancella un pesce esistente:
    @DeleteMapping("/delete-pesce/{id}")
    public ResponseEntity<Optional<Pesce>> deletePesce(@PathVariable Long id) {
        Optional<Pesce> pesceToDelete = pesceService.deletePesce(id);

        if (pesceToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pesceToDelete);
    }

    // Nutri i pesci:
    @PutMapping("/nutri-pesci")
    public ResponseEntity<List<Pesce>> nutriPesci() {
        List<Pesce> pesciDaNutrire = pesceService.nutriPesci();

        if (pesciDaNutrire == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pesciDaNutrire);
    }
}
