package it.prova.gestionesocieta.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService{

	@Autowired
	private DipendenteRepository dipendenteRepository;

	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendenti() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendente(Long id) {
		return dipendenteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
	}

	@Transactional
	public void rimuovi(Dipendente dipendenteInstance) {
		dipendenteRepository.delete(dipendenteInstance);
		
	}

	@Override
	public Dipendente dipendentePiuAnzianoDelleSocietaFondatePrimaDel(Date data) {
		return dipendenteRepository.dipendentePiuAnzianoConDataFondazionePrimaDi(data).get(0);
	}
}
