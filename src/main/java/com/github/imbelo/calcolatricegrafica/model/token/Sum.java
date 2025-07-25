package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Sum extends BinaryFunction{
	public Sum(Token left, Token right) {
		super(left, right);
		super.setType(OperationType.PLUS);
	}

	public Sum() {
		super(null,null);
		super.setType(OperationType.PLUS);
	}

	@Override
	public double getValue() {
		super.isWellFormed("sum not well formed");
		double left = realValue(super.getLeft());
		double right = realValue(super.getRight());
		return left + right;
	}
	@Override
	public String toString() {
		return "+";
	}

	
	
}
