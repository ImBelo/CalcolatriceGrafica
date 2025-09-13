package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
 
public class Difference extends BinaryFunction{

	public Difference(Token left, Token right) {
    super(left,right,"-");
	}

	public Difference() {
    super("-");
	}

  @Override
  public int getPriority() {
    return 2;
  }
  
	@Override
	public double getValue() {
		double left = realValue(super.getLeft());
		double right = realValue(super.getRight());
		return left-right;
	}
  @Override 
  public String toString() {
      return "-";
  }



}
