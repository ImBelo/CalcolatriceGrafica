package com.github.imbelo.calcolatricegrafica.model.interfaces;

import com.github.imbelo.calcolatricegrafica.model.parser.ParserResult;

public interface Parser<T extends Node<T>> {
	ParserResult<T> parse(Expression expr);
}
