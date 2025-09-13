package com.github.imbelo.calcolatricegrafica.model.interfaces;

import com.github.imbelo.calcolatricegrafica.model.parser.CompilerError;
import java.util.Optional; 
import java.util.List;

public interface ErrorFinder<T extends CompilerError,E> {
  Optional<T> check(Expression input);       
  Optional<T> check(List<Token> tokens); 
  void setAlphabet(Alphabet<E> alphabet);
}
