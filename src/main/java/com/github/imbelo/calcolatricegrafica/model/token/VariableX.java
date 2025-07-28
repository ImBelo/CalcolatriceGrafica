package com.github.imbelo.calcolatricegrafica.model.token;


public class VariableX extends Variable{
	 private static Variable me;
	 
	 public VariableX() {
	   super(TokenType.X());
	 }

	 public static Variable getInstance() {
	   if (me == null) {
	     me = new VariableX();
	   }

	   return me;
	   }
	 
	 public void setNum(double num) {
	   super.setNum(num);
	 }
}
