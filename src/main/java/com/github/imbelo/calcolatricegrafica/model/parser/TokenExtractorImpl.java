package com.github.imbelo.calcolatricegrafica.model.parser;

import com.github.imbelo.calcolatricegrafica.model.interfaces.TokenExtractor;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Alphabet;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Expression;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

import java.util.Optional;

public class TokenExtractorImpl implements TokenExtractor{

  private Alphabet<Token> alphabet;

  public TokenExtractorImpl(Alphabet<Token> alphabet){
    this.alphabet = alphabet;
  }
  public Optional<Token> extract(Expression expr) {
    Optional<Token> token = Optional.empty();
    for(Token t: alphabet.getSymbols()){
      String data = expr.get();
      String tokenString = t.getType().getData();
      if(data.startsWith(tokenString)){
        token = Optional.of(t);
        expr = ExpressionImpl.of(data.substring(tokenString.length()));
        break;
      }
    }
    return token;
  }
  
}
