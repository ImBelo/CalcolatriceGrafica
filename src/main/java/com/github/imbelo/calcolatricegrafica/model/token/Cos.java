package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Cos extends UnaryFunction {

	public Cos(Token child) {
    super(child,"cos");
	}

	public Cos() {
    super("cos");
	}

  @Override
  public int getPriority() {
    return 7;
  }

	@Override 
	public double getValue() {
		double child = realValue(super.getChild());
		return Math.cos(child);
	}
  @Override
  public String toString(){
    return "cos";
  }


}
