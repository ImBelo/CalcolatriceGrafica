package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Optional; 
import java.util.List;

public interface ErrorFinder<T> {
    Optional<T> check(Expression input);       
    Optional<T> check(List<Token> tokens); 
}
