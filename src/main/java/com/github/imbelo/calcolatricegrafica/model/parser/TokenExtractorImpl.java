package com.github.imbelo.calcolatricegrafica.model.parser;

import com.github.imbelo.calcolatricegrafica.model.interfaces.TokenExtractor;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Alphabet;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Expression;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

import java.util.Objects;
import java.util.Optional;

public class TokenExtractorImpl implements TokenExtractor{

  private Alphabet<Type<? extends Token>> alphabet;

  public TokenExtractorImpl(Alphabet<Type<? extends Token>> alphabet){
    this.alphabet = alphabet;
  }
  public Optional<Token> extract(Expression expr) {
    if(expr == null)
      return Optional.empty();
    Optional<Token> token = Optional.empty();
    for(Type<? extends Token> t: alphabet.getSymbols()){
      String data = expr.get();
      String tokenString = t.getData();
      if(data.startsWith(tokenString) && !tokenString.equals("")){
        token = Optional.of(t.create());
        expr.set(data.substring(tokenString.length()));
        break;
      }
    }
    return token;
  }
  
}
