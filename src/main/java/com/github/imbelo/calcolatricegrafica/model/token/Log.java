package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Log extends UnaryFunction{

	public Log(Token child) {
    super(child,TokenType.LOG());
	}

	public Log() {
    super(TokenType.LOG());
	}

	@Override
	public double getValue() {
		double child = realValue(super.getChild());
		return Math.log(child);
	}

	



}
