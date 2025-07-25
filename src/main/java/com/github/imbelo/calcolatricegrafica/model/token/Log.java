package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Log extends UnaryFunction{

	public Log(Token child) {
		super(child);
		super.setType(FunctionType.LOG);
	}

	public Log() {
		super.setType(FunctionType.LOG);
	}

	@Override
	public double getValue() {
		super.isWellFormed("Log not well formed");
		double child = realValue(super.getChild());
		return Math.log(child);
	}

	



}
