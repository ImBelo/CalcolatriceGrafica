package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Pow extends BinaryFunction{

	public Pow(Token left, Token right) {
    super(left,right,"^");
	}

	public Pow() {
    super("^");
	}

  @Override
  public int getPriority() {
    return 5;
  }

	@Override
	public double getValue() {
		double left = super.getLeft().getValue();
		double right = super.getRight().getValue();
		return left!=0 && right!=0?Math.pow(left, right):Double.NaN;
	}
  @Override
  public String toString(){
    return "^";
  }




	

}
