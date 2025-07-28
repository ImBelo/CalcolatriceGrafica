package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Sin extends UnaryFunction {

	public Sin(Token child) {
    super(child,TokenType.SIN());
	}

	public Sin() {
    super(TokenType.SIN());
	}

	@Override
	public double getValue() {
		return Math.sin(super.getChild().getValue());
	}

	

}
