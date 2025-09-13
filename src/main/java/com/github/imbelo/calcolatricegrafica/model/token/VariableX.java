package com.github.imbelo.calcolatricegrafica.model.token;


public class VariableX extends Variable{
  private static Variable me;
	
	public VariableX() {
    super("x");
	}

	public static Variable getInstance() {
	  if (me == null) {
	    me = new VariableX();
	  }
    return me;
	}
	 
  @Override
  public int getPriority() {
    return 10;
  }
  
	 public void setNum(double num) {
	   super.setNum(num);
	 }
}
