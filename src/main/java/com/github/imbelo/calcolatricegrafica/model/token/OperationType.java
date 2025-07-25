package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

import java.util.function.Supplier;

public enum OperationType implements Type {
	PLUS("+",Sum::new,1),
	MINUS("-",Difference::new,2),
	TIMES("*",Product::new,3),
	DIVIDED_BY("/",Division::new,4),
	RAISED_TO("^",Pow::new,5);
	private String data;
	private Supplier<BinaryFunction> supplier;
	private int priority;
	OperationType(String string,Supplier<BinaryFunction> supplier, int i) {
		this.supplier = supplier;
		this.data = string;
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
