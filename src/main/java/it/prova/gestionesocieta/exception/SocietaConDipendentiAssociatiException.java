package it.prova.gestionesocieta.exception;

public class SocietaConDipendentiAssociatiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SocietaConDipendentiAssociatiException() {}
	
	public SocietaConDipendentiAssociatiException (String messaggio) {
		super(messaggio);
	}
}
