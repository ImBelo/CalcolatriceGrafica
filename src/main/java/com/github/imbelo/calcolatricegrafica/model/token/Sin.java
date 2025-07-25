package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Sin extends UnaryFunction {

	public Sin(Token child) {
		super(child);
		super.setType(FunctionType.SINE);
	}

	public Sin() {
		super.setType(FunctionType.SINE);
	}

	@Override
	public double getValue() throws NotWellFormedFormulaException{
		super.isWellFormed("Sine not well formed");
		return Math.sin(super.getChild().getValue());
	}

	

}
