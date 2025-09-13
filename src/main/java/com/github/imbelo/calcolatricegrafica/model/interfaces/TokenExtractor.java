package com.github.imbelo.calcolatricegrafica.model.interfaces;

import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;
import java.util.Optional;

public interface TokenExtractor{
  Optional<Token> extract(Expression expr);
  void setAlphabet(Alphabet<Token> alphabet);
}

