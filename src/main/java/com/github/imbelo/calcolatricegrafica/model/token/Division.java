package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Division extends BinaryFunction {

	public Division(Token left, Token right) {
		super(left, right);
		super.setType(OperationType.DIVIDED_BY);
	}

	public Division() {
		super(null,null);
		super.setType(OperationType.DIVIDED_BY);
	}

	@Override
	public double getValue() throws NotWellFormedFormulaException{
		super.isWellFormed("Division not well formed ");
		double numerator = super.realValue(getLeft());
		double denominator = super.realValue(getRight());
		return denominator != 0? numerator/denominator:Double.NaN;
	}

	

}
