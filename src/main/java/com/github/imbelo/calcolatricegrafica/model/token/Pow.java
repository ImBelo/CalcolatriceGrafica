package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Pow extends BinaryFunction{

	public Pow(Token left, Token right) {
		super(left, right);
		super.setType(OperationType.RAISED_TO);
	}

	public Pow() {
		super(null,null);
		super.setType(OperationType.RAISED_TO);
	}

	@Override
	public double getValue() throws NotWellFormedFormulaException{
		super.isWellFormed("Power not well formed");
		double left = super.getLeft().getValue();
		double right = super.getRight().getValue();
		return left!=0 && right!=0?Math.pow(left, right):Double.NaN;
	}

	

}
