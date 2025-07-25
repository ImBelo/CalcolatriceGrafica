package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Variable;

import java.util.function.Supplier;

public enum VariableType implements Type {
	X("x",VariableX::getInstance,12),
	Y("y",VariableY::getInstance,13);
	private String data;
	private Supplier<Variable> supplier;
	private int priority;
	VariableType(String string, Supplier<Variable> supplier, int i) {
		this.data = string;
		this.supplier = supplier;
		this.priority = i;
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
