package com.github.imbelo.calcolatricegrafica.model.parser;

import com.github.imbelo.calcolatricegrafica.model.interfaces.TokenExtractor;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Alphabet;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Expression;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.token.TypeRegistry;
import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;
import com.github.imbelo.calcolatricegrafica.model.factories.NumberFactory;

import java.util.Optional;

public class TokenExtractorImpl implements TokenExtractor{

  private Alphabet<Token> alphabet;

  public TokenExtractorImpl(Alphabet<Token> alphabet){
    this.alphabet = alphabet;
  }
  public TokenExtractorImpl(){
  }
  public void setAlphabet(Alphabet<Token> alphabet){
    this.alphabet = alphabet;
  }
  public Optional<Token> extract(Expression expr) {
    if(this.alphabet == null)
      throw new UnsupportedOperationException("TokenExtractor Requires an alphabet to work with");
    if(expr == null)
      return Optional.empty();
    Character currentChar = expr.get().charAt(0);
		
		if (Character.isDigit(currentChar)) 
			return NumberFactory.createNumber(expr);

    Optional<Token> token = Optional.empty();
    for(Token t: this.alphabet.getSymbols()){
      String data = expr.get();
      String tokenString = t.getData();
      if(data.startsWith(tokenString) && !tokenString.equals("")){
        token = Optional.of(TypeRegistry.create(tokenString));
        expr.set(data.substring(tokenString.length()));
        break;
      }
    }
    return token;
  }
  
}
