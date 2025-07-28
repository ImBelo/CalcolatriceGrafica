package com.github.imbelo.calcolatricegrafica.model.interfaces;

import com.github.imbelo.calcolatricegrafica.model.parser.CompilerError;
import java.util.Optional; 
import java.util.List;

public interface ErrorFinder<T extends CompilerError> {
    Optional<T> check(Expression input);       
    Optional<T> check(List<Token> tokens); 
}
