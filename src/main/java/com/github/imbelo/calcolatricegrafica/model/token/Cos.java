package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Cos extends UnaryFunction {

	public Cos(Token child) {
		super(child);
		super.setType(FunctionType.COSINE);
	}

	public Cos() {
		super.setType(FunctionType.COSINE);
	}

	@Override 
	public double getValue() {
		super.isWellFormed("cosine not well formed");
		double child = realValue(super.getChild());
		return Math.cos(child);
	}


}
