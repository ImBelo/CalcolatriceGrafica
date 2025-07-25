package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Difference extends BinaryFunction{

	public Difference(Token left, Token right) {
		super(left, right);
		this.setType(OperationType.MINUS);
	}

	public Difference() {
		super(null,null);
		super.setType(OperationType.MINUS);
	}

	@Override
	public double getValue() {
		super.isWellFormed("Difference not well formed");
		double left = realValue(super.getLeft());
		double right = realValue(super.getRight());
		return left-right;
	}



}
