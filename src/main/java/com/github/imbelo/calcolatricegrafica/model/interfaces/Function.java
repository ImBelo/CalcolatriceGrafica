package com.github.imbelo.calcolatricegrafica.model.interfaces;

public interface Function extends Tree<Token>{
	double evaluateAt(double ...num);
}
