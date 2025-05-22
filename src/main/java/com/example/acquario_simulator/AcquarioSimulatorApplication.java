package com.example.acquario_simulator;

import com.example.acquario_simulator.entity.Acquario;
import com.example.acquario_simulator.entity.Pesce;
import com.example.acquario_simulator.repository.AcquarioRepository;
import com.example.acquario_simulator.repository.PesceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AcquarioSimulatorApplication implements ApplicationRunner {

	@Autowired
	private PesceRepository pesceRepository;

	@Autowired
	private AcquarioRepository acquarioRepository;

	private Integer pesciMortiPerFame = 0;
	private Integer pesciMortiPerMalattia = 0;

	public static void main(String[] args) {
		SpringApplication.run(AcquarioSimulatorApplication.class, args);
	}

	public void populatePesci() {
		List<Pesce> listaPesci = new ArrayList<>();

		int pesciMax = 20;

		for (int i = 0; i < pesciMax; i++) {
			long livelloFame = (long) (Math.random() * 100);
			long livelloSalute = (long) (Math.random() * 100);
			int eta = (int) (Math.random() * 100);
			Pesce pesce = new Pesce(null, "pesce-" + i, "pesce palla", livelloFame, livelloSalute, eta);

			listaPesci.add(pesce);
		}
		pesceRepository.saveAll(listaPesci);
	}

	public void createAcquario() {
		Acquario acquario = new Acquario(null, 20, 100L, 18.0);
		acquarioRepository.save(acquario);
	}

	public void simulateEcosistema() {
		// variabile tempo in giorni:
		Integer time = 0;

		// il massimo di giorni che può durare il ciclo:
		Integer giorniLimite = 50;

		// inizializzo una lista di pesci:
		List<Pesce> listaPesci;

		// faccio passare 1000 giorni e ad ogni giorno verifico l'ecosistema:
		while (time < giorniLimite) {
			time = time + 1;
			updateEcosistema();

			// a ogni iterazione aggiorno la mia lista di pesci:
			listaPesci = pesceRepository.findAll();

			// se non ci sono più pesci vivi dentro l'acquario, esco dal ciclo:
			if (listaPesci.isEmpty())
				break;
		}

		Integer pesciRimasti = pesceRepository.findAll().size();

		// log finale:
		System.out.println("Il tuo acquario è durato ben " + time + " giorni!");
		System.out.println("In totale sono rimasti " + pesciRimasti + " pesci nell'acquario.");
		System.out.println("In totale sono morti " + pesciMortiPerFame + " pesci per fame.");
		System.out.println("In totale sono morti " + pesciMortiPerMalattia + " pesci per malattia.");
	}

	@Transactional
	public void updateEcosistema() {
		// In questo metodo verifico tutti i parametri dell'ecosistema e lo aggiorna in base ad essi
		Optional<Acquario> acquario = acquarioRepository.findById(1L);
		List<Pesce> listaPesci = pesceRepository.findAll();

		// Creo due nuove liste di pesci, una per i pesci ancora vivi e l'altra per quelli morti da scartare:
		List<Pesce> pesciDaSalvare = new ArrayList<>();
		List<Pesce> pesciDaEliminare = new ArrayList<>();

		// l'acquario giorno dopo giorno diventa sempre più sporco:
		if (acquario.isPresent()) {
			acquario.get().setLivelloPulizia(Math.max(0, acquario.get().getLivelloPulizia() - 3));

			// salvo il nuovo stato dell'acquario:
			acquarioRepository.save(acquario.get());
		}

		// simulo le condizioni dei pesci giorno dopo giorno:
		for (Pesce pesce : listaPesci) {
			// la fame dei pesci aumenta giorno dopo giorno:
			pesce.setLivelloFame(Math.min(100, pesce.getLivelloFame() + 3));

			// se l'acquario è troppo sporco, la salute dei pesci diminuisce:
			if (acquario.isPresent()) {
				if (acquario.get().getLivelloPulizia() <= 20) {
					pesce.setLivelloSalute(Math.max(0, pesce.getLivelloSalute() - 3));
				}
			}

			// se la salute dei pesci è troppo bassa o se la fame è troppo alta, i pesci muoiono:
			if (pesce.getLivelloFame() > 95) {
				pesciMortiPerFame++;
				pesciDaEliminare.add(pesce);
			} else if (pesce.getLivelloSalute() < 5) {
				pesciMortiPerMalattia++;
				pesciDaEliminare.add(pesce);
			} else {
				pesciDaSalvare.add(pesce);
			}
		}

		// salvo il nuovo stato dei pesci:
		pesceRepository.saveAll(pesciDaSalvare);
		pesceRepository.deleteAll(pesciDaEliminare);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		populatePesci();
		createAcquario();
		simulateEcosistema();
	}
}
