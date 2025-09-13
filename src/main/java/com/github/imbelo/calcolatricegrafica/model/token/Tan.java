package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Tan extends UnaryFunction {

	public Tan(Token child) {
		super(child,"tan");
	}

	public Tan() {
    super("tan");
	}

  @Override
  public int getPriority() {
    return 9;
  }
	@Override
	public double getValue() {
		double child = realValue(super.getChild());
		return Math.tan(child);
	}
  @Override
  public String toString(){
    return "tan";
  }

	

}
