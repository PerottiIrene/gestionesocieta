package it.prova.gestionesocieta.service;

import java.util.Date;
import java.util.List;

import org.hibernate.internal.TypeLocatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.exception.SocietaConDipendentiAssociatiException;
import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {
	
	@Autowired
	private DipendenteService dipendenteService;
	
	@Autowired
	private SocietaService societaService;
	
	
	public void testInserisciNuovaSocieta() {
		
		Societa societaDaInserire=new Societa("societa1","via roma",new Date());
		if (societaDaInserire.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		
		societaService.inserisciNuovo(societaDaInserire);
		if(societaDaInserire.getId() == null ||societaDaInserire.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		System.out.println("testInserisciNuovaSocieta........OK");
	}
	
	public void testFindByExampleSocieta() {
		
		Societa societaDaInserire=new Societa("nuovaSocieta","via roma",new Date());
		if (societaDaInserire.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		
		societaService.inserisciNuovo(societaDaInserire);
		if(societaDaInserire.getId() == null ||societaDaInserire.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");
		
		List<Societa> listaRisultati=societaService.findByExample(new Societa("nuova"));
		if(listaRisultati.size() != 1)
			throw new RuntimeException("testFindByExampleSocieta...failed");

		System.out.println("testFindByExampleSocieta........OK");
	}
	
	public void testRimozioneSocieta() {
		
		Societa societaDaRimuovere=new Societa("nuovaSocieta","via roma",new Date());
		societaService.inserisciNuovo(societaDaRimuovere);
		if (societaDaRimuovere.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		
		try {
		societaService.rimuovi(societaDaRimuovere);	
		}catch (SocietaConDipendentiAssociatiException e) {
			e.printStackTrace();
		}
		
		Societa societaSupposedToBeRemoved= societaService.caricaSingolaSocieta(societaDaRimuovere.getId());
		if(societaSupposedToBeRemoved != null)
			throw new RuntimeException("testRimozioneSocieta failed");
		
		System.out.println("testRimozioneSocieta....OK");
	}
	
	public void testInserimentoDipendente() {
		
		Societa societa1=new Societa("nuovaSocieta","via roma",new Date());
		societaService.inserisciNuovo(societa1);
		
		Dipendente nuovoDipedente=new Dipendente("irene","rossi",new Date(),13000,societa1);
		dipendenteService.inserisciNuovo(nuovoDipedente);
		if(nuovoDipedente.getId() == null || nuovoDipedente.getId() < 0)
			throw new RuntimeException("testInserimentoDipendente...failed: inserimento fallito");
			
	    System.out.println("testInserimentoDipendente...OK");
		
	}
	
	public void testModificaDipendente() {
		
		Societa societa1=new Societa("nuovaSocieta","via roma",new Date());
		societaService.inserisciNuovo(societa1);
		
		Dipendente dipendenteDaModificare=new Dipendente("mario", "rossi", new Date(),15000,societa1);
		dipendenteService.inserisciNuovo(dipendenteDaModificare);
		if(dipendenteDaModificare.getId() == null || dipendenteDaModificare.getId() < 0)
			throw new RuntimeException("testInserimentoDipendente...failed: inserimento fallito");
		
		String nuovoCognome="verdi";
		dipendenteDaModificare.setCognome(nuovoCognome);
		
		dipendenteService.aggiorna(dipendenteDaModificare);
		
		Dipendente dipendenteReloaded = dipendenteService.caricaSingoloDipendente(dipendenteDaModificare.getId());
		if (!dipendenteDaModificare.getCognome().equals(dipendenteReloaded.getCognome()))
			throw new RuntimeException("testModificaDipendente fallito");
		
		System.out.println("testModificaDipendente...OK");
		
	}
	
	

}
