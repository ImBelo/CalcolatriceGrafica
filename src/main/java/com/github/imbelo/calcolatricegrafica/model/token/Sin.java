package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Sin extends UnaryFunction {

	public Sin(Token child) {
    super(child,"sin");
	}

	public Sin() {
    super("sin");
	}
  @Override
  public int getPriority() {
    return 6;
  }

	@Override
	public double getValue() {
		return Math.sin(super.getChild().getValue());
	}
  @Override
  public String toString(){
    return "sin";
  }

	

}
