package com.github.imbelo.calcolatricegrafica.model.factories;

import com.github.imbelo.calcolatricegrafica.model.parser.TokenFinder;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.token.*;

import java.util.List;
import java.util.Optional;


public class TokenTreeFactory implements TreeFactory<Token>{
  private final TokenFinder finder;
    
  public TokenTreeFactory(TokenFinder finder) {
    this.finder = finder;
  }
  @Override
	public Optional<Token> createTree(List<Token> tokens) {
		Optional<Token> token = Optional.empty();
		Optional<Integer> location = Optional.empty();	// index of the token we are searching for
		if(tokens == null || tokens.contains(null))
			return token;	
		token = finder.find(tokens,this);
		if(token.isPresent())
			return token;
    location = finder.scanFromRight(tokens, TokenType.NUMBER());
		if (location.isPresent()) {
			// creation of number
			return Optional.of(new MyNumber(tokens.get(location.get()).getValue()));
		}
    return token;
  }
	
	
	
	

}
