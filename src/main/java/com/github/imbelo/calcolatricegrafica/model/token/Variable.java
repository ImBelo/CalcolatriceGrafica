package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

public class Variable extends MyNumber {
	public Variable(Type<Variable> type) {
	  super(type);
	}

  @Override
  public double getValue(){
    return super.getValue(); 
  }
	public void setValue(double num) {
		this.setNum(num);
	}
	
	

}
