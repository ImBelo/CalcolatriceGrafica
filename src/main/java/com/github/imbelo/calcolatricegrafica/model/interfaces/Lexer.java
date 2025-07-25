package com.github.imbelo.calcolatricegrafica.model.interfaces;


import com.github.imbelo.calcolatricegrafica.model.parser.TokenizationResult;

public interface Lexer{
	TokenizationResult tokenize(Expression expr); 
}
