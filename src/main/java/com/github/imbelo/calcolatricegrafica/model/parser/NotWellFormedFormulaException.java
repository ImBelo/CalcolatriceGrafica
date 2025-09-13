package com.github.imbelo.calcolatricegrafica.model.parser;

public class NotWellFormedFormulaException extends RuntimeException{ 
	private static final long serialVersionUID = 1L;
	public NotWellFormedFormulaException(String errorMessage) {
		super(errorMessage);
		
	}
	public NotWellFormedFormulaException() {
		super();
	};

}
