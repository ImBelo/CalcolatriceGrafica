package com.github.imbelo.calcolatricegrafica.model.token;


public class VariableY extends Variable {
  private static Variable me;

	public VariableY() {
    super("y");
	}

  public static Variable getInstance() {
    if (me == null) {
	    me = new VariableY();
	  }
	  return me;
	}
  @Override
  public int getPriority() {
    return 11;
  }
	    
	public void setNum(double num) {
	  super.setNum(num);
	}

}
