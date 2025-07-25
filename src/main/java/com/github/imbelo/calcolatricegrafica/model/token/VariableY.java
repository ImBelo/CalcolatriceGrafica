package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Variable;

public class VariableY extends AbstractVariable implements Variable {
	 private static Variable me;
	 

	    public VariableY() {
	    	super(VariableType.Y);
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
