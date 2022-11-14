package it.prova.gestionesocieta.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente>{
	
	@Query("select d from Societa s join s.dipendenti d where dataFondazione < ?1 order by d.dataAssunzione ASC")
	List<Dipendente> dipendentePiuAnzianoConDataFondazionePrimaDi(Date data);

}
