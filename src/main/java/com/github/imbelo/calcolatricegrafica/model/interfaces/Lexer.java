package com.github.imbelo.calcolatricegrafica.model.interfaces;


import java.util.List;
import java.util.Optional;

public interface Lexer{
	Optional<List<Token>> tokenize(Expression expr); 
  String getError();
}
