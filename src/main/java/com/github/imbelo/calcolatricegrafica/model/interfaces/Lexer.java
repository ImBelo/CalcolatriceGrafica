package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.List;
import java.util.Optional;

public interface Lexer{
	public Optional<List<Token>> tokenize(Expression expr); 
}
