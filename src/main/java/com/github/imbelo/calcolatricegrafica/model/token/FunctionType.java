package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

import java.util.function.Supplier;

public enum FunctionType implements Type {
	SINE("sin",Sin::new,6),
	COSINE("cos",Cos::new,7), 
	TANGENT("tan",Tan::new,8), 
	LOG("log",Log::new,9);
	
	private String data;
	private Supplier<UnaryFunction> supplier;
	private int priority;
	FunctionType(String string, Supplier<UnaryFunction> supplier, int priority) {
		this.data = string;
		this.supplier = supplier;
		this.priority = priority;;
	}
	@Override
	public String getData() {
		return this.data;
	}

	@Override
	public Supplier<? extends Token> getSupplier() {
		return this.supplier;
	}

	@Override
	public int getPriority() {
		return this.priority;
	}

}
