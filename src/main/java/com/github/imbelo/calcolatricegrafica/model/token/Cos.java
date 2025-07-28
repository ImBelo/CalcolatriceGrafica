package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Cos extends UnaryFunction {

	public Cos(Token child) {
		super(child,TokenType.COS());
	}

	public Cos() {
		super(TokenType.COS());
	}

	@Override 
	public double getValue() {
		double child = realValue(super.getChild());
		return Math.cos(child);
	}


}
