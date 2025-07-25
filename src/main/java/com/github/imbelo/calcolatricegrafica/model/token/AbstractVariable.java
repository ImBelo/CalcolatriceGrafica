package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Variable;

public abstract class AbstractVariable extends MyNumber implements Variable {
	public AbstractVariable(Type t) {
		super(0.0);
		super.setType(t);
		
	}
	
	public void setValue(double num) {
		this.setNum(num);
	}
	
	

}
