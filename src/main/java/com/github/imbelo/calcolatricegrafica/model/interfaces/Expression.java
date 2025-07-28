package com.github.imbelo.calcolatricegrafica.model.interfaces;

import com.github.imbelo.calcolatricegrafica.model.token.Arity;

public interface Expression {
	String get();
  void set(String string);
  void set(Expression expression);
	Arity getArity();
  Boolean isEmpty(); 
}
