package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

public abstract class AbstractToken implements Token{
	private Type type;
	private String data;
	public Type getType() { 
		return type;
	}
	public void setType(Type type) {
		this.type = type;
		this.data = type.getData();
	}
	public String getData() { 
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public abstract double getValue();
	public double realValue(Token t) {
		return t != null ? t.getValue() :Double.NaN;
	}
	
	
	


	

}
