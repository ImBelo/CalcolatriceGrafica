package com.github.imbelo.calcolatricegrafica.model.token;

public class OpenParentheses extends AbstractParentheses{
	public OpenParentheses(){
		super(TokenType.OPEN_PARENTHESES());
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
