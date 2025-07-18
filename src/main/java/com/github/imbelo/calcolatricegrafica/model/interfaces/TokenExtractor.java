package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Optional;

public interface TokenExtractor{
  Optional<Token> extract(Expression expr);
}

