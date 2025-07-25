package com.github.imbelo.calcolatricegrafica.model.factories;

import com.github.imbelo.calcolatricegrafica.model.parser.TokenFinder;
import com.github.imbelo.calcolatricegrafica.model.token.MyNumber;
import com.github.imbelo.calcolatricegrafica.model.token.NotWellFormedFormulaException;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.token.TokenType;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;

import java.util.List;
import java.util.Optional;


public interface TokenTreeFactory extends TreeFactory<Token>{
	public static Optional<Token> createTreeNode(NodeFinder<Token> nodeFinder, List<Token> tokens) {
		Optional<Token> token = Optional.empty();
		int location = 0;	// index of the token we are searching for
		if(tokens == null || tokens.contains(null))
			return token;	
		token = nodeFinder.findToken(tokens);
		if(token.isPresent())
			return token;
    location = tokenFinder.scanFromRight(tokens, TokenType.NUMBER);
		if (location != -1) {
			// creation of number
			return Optional.of(new MyNumber(tokens.get(location).getValue()));
		}
    return token;
  }
	
	
	
	

}
