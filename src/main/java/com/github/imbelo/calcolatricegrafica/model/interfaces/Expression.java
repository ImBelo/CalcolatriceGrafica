package com.github.imbelo.calcolatricegrafica.model.interfaces;

import com.github.imbelo.calcolatricegrafica.model.token.Arity;

public interface Expression {
	String get();
	Arity getArity();
  Boolean isEmpty(); 
}
