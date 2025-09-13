package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Log extends UnaryFunction{

	public Log(Token child) {
    super(child,"log");
	}

	public Log() {
    super("log");
	}

  @Override
  public int getPriority() {
    return 8;
  }
	@Override
	public double getValue() {
		double child = realValue(super.getChild());
		return Math.log(child);
	}
  @Override
  public String toString(){
    return "log";
  }

	



}
