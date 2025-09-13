package com.github.imbelo.calcolatricegrafica.model.token;


public class Variable extends MyNumber {
	public Variable(String data) {
    super(data);
	}

  @Override
  public double getValue(){
    return super.getValue(); 
  }
	public void setValue(double num) {
		this.setNum(num);
	}
	
	

}
