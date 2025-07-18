package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.function.Supplier;

public interface Type {
	public String getData();
	public Supplier<? extends Token> getSupplier();
	public int getPriority();
}
