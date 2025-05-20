package com.example.acquario_simulator.controller;

import com.example.acquario_simulator.entity.Acquario;
import com.example.acquario_simulator.entity.MostraParametri;
import com.example.acquario_simulator.entity.Pesce;
import com.example.acquario_simulator.service.AcquarioService;
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

    @Autowired
    private AcquarioService acquarioService;

    // Aggiungi un nuovo pesce:
    @PostMapping("/create-pesce")
    public ResponseEntity<Pesce> createPesce(@RequestBody Pesce pesce) {
        Pesce pesceToAdd = pesceService.createPesce(pesce);
        return ResponseEntity.ok(pesceToAdd);
    }

    // Ottieni tutti i pesci:
    @GetMapping("/find-pesci")
    public ResponseEntity<List<Pesce>> getPesci() {
        List<Pesce> pesciToFind = pesceService.getPesci();
        return ResponseEntity.ok(pesciToFind);
    }

    // Cancella un pesce esistente:
    @DeleteMapping("/delete-pesce/{id}")
    public ResponseEntity<Optional<Pesce>> deletePesce(@PathVariable Long id) {
        Optional<Pesce> pesceToDelete = pesceService.deletePesce(id);

        if (pesceToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pesceToDelete);
    }

    // Modifica un pesce esistente:
    @PutMapping("/update-pesce/{id}")
    public ResponseEntity<Optional<Pesce>> updatePesce(@PathVariable Long id, @RequestBody Pesce pesceDetails) {
        Optional<Pesce> pesceToUpdate = pesceService.updatePesce(id, pesceDetails);

        if (pesceToUpdate.isPresent()) {
            return ResponseEntity.ok(pesceToUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    // Nutri i pesci:
    @PutMapping("/nutri-pesci/{acquarioId}")
    public ResponseEntity<List<Pesce>> nutriPesci(@PathVariable Long acquarioId, @RequestParam Long livelloFame, @RequestParam Long livelloPulizia) {
        List<Pesce> pesciDaNutrire = pesceService.nutriPesci(acquarioId, livelloFame, livelloPulizia);
        Optional<Acquario> acquarioOptional = acquarioService.getAcquarioById(acquarioId);

        if (acquarioOptional.isPresent()) {
            return ResponseEntity.ok(pesciDaNutrire);
        }
        return  ResponseEntity.notFound().build();
        /*if (pesciDaNutrire == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pesciDaNutrire);*/
    }

    // Mostra parametri ecosistema:
    @GetMapping("/mostra-parametri/{acquarioId}")
    public ResponseEntity<MostraParametri> mostraParametri(@PathVariable Long acquarioId) {
        MostraParametri parametri = pesceService.mostraParametri(acquarioId);

        if (parametri == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parametri);
    }
}
