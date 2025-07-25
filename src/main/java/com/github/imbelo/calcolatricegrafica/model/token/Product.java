package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Product extends BinaryFunction{

	public Product(Token left, Token right) {
		super(left, right);
		this.setType(OperationType.TIMES);
	}
	public Product() {
		super(null,null);
		super.setType(OperationType.TIMES);
	}
	public double getValue() {
		super.isWellFormed("Product not well formed");
		double left = realValue(super.getLeft());
		double right = realValue(super.getRight());
		return left*right;
	}


}
