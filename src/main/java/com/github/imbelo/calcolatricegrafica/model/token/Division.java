package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Division extends BinaryFunction {

	public Division(Token left, Token right) {
    super(left,right,TokenType.DIVIDE());
	}

	public Division() {
    super(TokenType.DIVIDE());
	}

	@Override
	public double getValue() {
		double numerator = super.realValue(getLeft());
		double denominator = super.realValue(getRight());
		return denominator != 0? numerator/denominator:Double.NaN;
	}

	

}
