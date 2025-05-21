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

	public static void main(String[] args) {
		SpringApplication.run(AcquarioSimulatorApplication.class, args);
	}

	public void populatePesci() {
		List<Pesce> listaPesci = new ArrayList<>();

		int counter = 10;

		for (int i = 0; i < counter; i++) {
			long livelloFame = (long) (Math.random() * 100);
			long livelloSalute = (long) (Math.random() * 100);
			int eta = (int) (Math.random() * 100);
			Pesce pesce = new Pesce(null, "pesce-" + i, "pesce palla", livelloFame, livelloSalute, eta);

			listaPesci.add(pesce);
		}
		pesceRepository.saveAll(listaPesci);
	}

	public void createAcquario() {
		Acquario acquario = new Acquario(null, 10, 75L, 18.0);
		acquarioRepository.save(acquario);
	}

	public void simulaEcosistema() {
		// variabile tempo in giorni:
		Integer time = 0;

		// il massimo di giorni che può durare il ciclo:
		Integer giorniLimite = 5;

		// inizializzo una lista di pesci:
		List<Pesce> listaPesci;

		// faccio passare 1000 giorni e ad ogni giorno verifico l'ecosistema:
		while (time < giorniLimite) {
			time = time + 1;
			aggiornaEcosistema();

			// a ogni iterazione aggiorno la mia lista di pesci:
			listaPesci = pesceRepository.findAll();

			// se non ci sono più pesci vivi dentro l'acquario, esco dal ciclo:
			if (listaPesci.isEmpty())
				break;
		}

		Integer pesciRimasti = pesceRepository.findAll().size();

		System.out.println("Il tuo acquario è durato ben " + time + " giorni!");
		System.out.println("In totale sono rimasti " + pesciRimasti + " pesci nell'acquario.");
	}

	@Transactional
	public void aggiornaEcosistema() {
		// In questo metodo verifico tutti i parametri dell'ecosistema e lo aggiorna in base ad essi
		List<Pesce> listaPesci = pesceRepository.findAll();
		Optional<Acquario> acquario = acquarioRepository.findById(1L);

		// Creo due nuove liste di pesci, una per i pesci ancora vivi e l'altra per quelli morti da scartare:
		List<Pesce> pesciDaSalvare = new ArrayList<>();
		List<Pesce> pesciDaEliminare = new ArrayList<>();

		for (Pesce pesce : listaPesci) {
			pesce.setLivelloSalute(pesce.getLivelloSalute() - 1);
			pesce.setLivelloFame(pesce.getLivelloFame() + 10);

			// se la salute dei pesci è troppo bassa o se hanno troppa fame i pesci muoiono:
			if (pesce.getLivelloSalute() < 5 || pesce.getLivelloFame() > 95) {
				//pesceRepository.delete(pesce);
				pesciDaEliminare.add(pesce);
			} else {
				pesciDaSalvare.add(pesce);
			}

			// salvo il nuovo stato dei pesci:
			//pesceRepository.saveAll(listaPesci);
			pesceRepository.saveAll(pesciDaSalvare);
			pesceRepository.deleteAll(pesciDaEliminare);
		}

		// l'acquario giorno dopo giorno diventa sempre più sporco:
		if (acquario.isPresent()) {
			acquario.get().setLivelloPulizia(Math.max(0, acquario.get().getLivelloPulizia() - 5));

			// salvo il nuovo stato dell'acquario:
			acquarioRepository.save(acquario.get());
		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		populatePesci();
		createAcquario();
		simulaEcosistema();
	}
}
