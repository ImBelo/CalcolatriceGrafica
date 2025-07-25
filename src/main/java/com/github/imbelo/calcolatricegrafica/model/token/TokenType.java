package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Variable;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public enum TokenType implements Type {
	OPEN_PARENTHESES("(",OpenParentheses::new,10),
	CLOSED_PARENTHESES(")",ClosedParentheses::new,11),
	NUMBER(" ",()->new MyNumber(0.0),14); 

	private String data;
	private Supplier<Token> supplier;
	private int priority;
	private TokenType(String string){
		this.data = string;	
	}
	private TokenType(String string,Supplier<Token> supp,int priority) {
		this.data = string;	
		this.supplier = supp;
		this.priority = priority;
	}
	public int getPriority() {
		return priority;
	}
	@Override
	public String getData() {
		return data;
	}
	public Supplier<Token> getSupplier(){
		return this.supplier;
	}

}
