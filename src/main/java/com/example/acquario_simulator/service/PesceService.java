package com.example.acquario_simulator.service;

import com.example.acquario_simulator.entity.Acquario;
import com.example.acquario_simulator.entity.MostraParametri;
import com.example.acquario_simulator.entity.Pesce;
import com.example.acquario_simulator.repository.AcquarioRepository;
import com.example.acquario_simulator.repository.PesceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PesceService {

    private static final Long MAXFAME = 100L;

    @Autowired
    private PesceRepository pesceRepository;

    @Autowired
    private AcquarioRepository acquarioRepository;

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
    public List<Pesce> nutriPesci(Long idAqcuario, Long livelloFame, Long livelloPulizia) {
        // Trovo tutti i pesci dell'acquario:
        List<Pesce> listaPesci = pesceRepository.findAll();

        // Creo un arrayList vuoto di pesci nutriti:
        List<Pesce> pesciNutriti = new ArrayList<>();

        // Ciclo tutti i pesci e ad ognuno aggiungo il livello di fame in input:
        for (Pesce pesce : listaPesci) {
            pesce.setLivelloFame(pesce.getLivelloFame() + livelloFame);

            // Se il livello di fame è maggiore della fame massima, lo imposto a fame massima:
            if (pesce.getLivelloFame() > MAXFAME) {
                pesce.setLivelloFame(MAXFAME);
            }

            // Salvo i pesci nutriti nel nuovo arrayList:
            Pesce pesceNutrito = pesceRepository.save(pesce);
            pesciNutriti.add(pesceNutrito);
        }

        // Trovo l'acquario per id (associazione pesci-acquario ancora da fare):
        Optional<Acquario> acquarioOptional = acquarioRepository.findById(idAqcuario);

        // Diminuisco il livello di pulizia dell'acquario col valore in input:
        if (acquarioOptional.isPresent()) {
            Long livelloPuliziaIniziale = acquarioOptional.get().getLivelloPulizia();
            acquarioOptional.get().setLivelloPulizia(livelloPuliziaIniziale - livelloPulizia);
            acquarioRepository.save(acquarioOptional.get());
        }

        return pesciNutriti;
    }

    // Monitorare l'ecosistema:
    public MostraParametri mostraParametri(Long idAcquario) {
        // Obiettivo: travasare i dati all'interno di un oggetto parametri

        // Step 0 - Trovo l'acquario per id e la lista di pesci:
        Optional<Acquario> acquario = acquarioRepository.findById(idAcquario);
        List<Pesce> listaPesci = pesceRepository.findAll();

        // Step 1 - creare un oggetto parametri:
        MostraParametri parametri = new MostraParametri();

        // Step 2 - copio i dati (mi sono caricato l'acquario e i pesci) all'interno dell'oggetto:
        if (acquario.isPresent()){
            Long livelloPulizia = acquario.get().getLivelloPulizia();
            Double temperaturaAcqua = acquario.get().getTemperaturaAcqua();
            parametri.setTemperaturaAcqua(temperaturaAcqua);
            parametri.setLivelloPulizia(livelloPulizia);
        }

        parametri.setListaPesci(listaPesci);

        // Step 3 - ritorno l'oggetto con i parametri copiati:
        return parametri;
    }
}