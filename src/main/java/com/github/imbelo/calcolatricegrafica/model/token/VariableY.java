package com.github.imbelo.calcolatricegrafica.model.token;


public class VariableY extends Variable {
  private static Variable me;

	public VariableY() {
	  super(TokenType.Y());

	}

  public static Variable getInstance() {
    if (me == null) {
	    me = new VariableY();
	  }
	  return me;
	}
	    
	public void setNum(double num) {
	  super.setNum(num);
	}

}
