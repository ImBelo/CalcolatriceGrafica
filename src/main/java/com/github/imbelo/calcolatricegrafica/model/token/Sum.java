package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Sum extends BinaryFunction{
	public Sum(Token left, Token right) {
    super(left,right,"+");
	}

	public Sum() {
    super("+");
	}
  @Override
  public int getPriority() {
    return 1;
  }

	@Override
	public double getValue() {
		double left = realValue(super.getLeft());
		double right = realValue(super.getRight());
		return left + right;
	}
	@Override
	public String toString() {
		return "+";
	}

	
	
}
