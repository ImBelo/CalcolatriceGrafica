package com.github.imbelo.calcolatricegrafica.model.token;

public class OpenParentheses extends AbstractParentheses{
	public OpenParentheses(){
    super("(");
	}

  @Override
  public int getPriority() {
    return 13;
  }
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (obj == null || !(obj instanceof OpenParentheses)) 
            return false;
		return true;   
		
	}
	
	
}
