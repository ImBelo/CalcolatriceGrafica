package com.github.imbelo.calcolatricegrafica.model.token;

public class ClosedParentheses extends AbstractParentheses{
	public ClosedParentheses() {
		super(TokenType.CLOSED_PARENTHESES());
	}
	
	@Override 
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (obj == null || !(obj instanceof ClosedParentheses)) 
            return false;
		return true;	
		
	}

}
