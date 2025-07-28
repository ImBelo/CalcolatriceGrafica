package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

public abstract class AbstractToken implements Token{
  private String data;
  private Type<? extends Token> type;
  public AbstractToken(Type<? extends Token> type){
    this.type = type;
  }
  public Type<? extends Token> getType(){
    return this.type;
  }
  public String getData(){
    return this.data;
  }
	public abstract double getValue();
	public double realValue(Token t) {
		return t != null ? t.getValue() :Double.NaN;
	}
	
	
	


	

}
