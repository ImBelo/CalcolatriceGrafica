package com.github.imbelo.calcolatricegrafica.model.parser;


import com.github.imbelo.calcolatricegrafica.model.interfaces.NodeFinder;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.TreeFactory;
import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;

import java.util.Optional;
import java.util.List;
import com.github.imbelo.calcolatricegrafica.model.token.*;


public class TokenFinder implements NodeFinder<Token>{
  private AlphabetToken alphabet;

  public TokenFinder(AlphabetToken alphabet){
    this.alphabet = alphabet;
  }
  @Override
  public Optional<Integer> find(List<Token> tokens){
    Optional<Integer> location;
    for (Token token: alphabet.getSymbolsInPriorityOrder()) {
      location = scanFromRight(tokens, token);
      if(location.isEmpty())
        continue;
      else
        return location;
       
  }
    return Optional.empty();
  }


  public Optional<Integer> scanFromRight(List<Token> tokens,Token type) {
		int openParentheses = 0;
    List<Token> tokenAlphabet = this.alphabet.getSymbols();
    if(!tokenAlphabet.contains(type)){
      return Optional.empty();
    }
		for (int i = tokens.size()-1; i >= 0;i--) {
      Token t = tokens.get(i);

			if (t != null) {
				if (t instanceof ClosedParentheses) 
					openParentheses--;
				else if (t instanceof OpenParentheses) 
					openParentheses++;
					// if openParentheses is 0 it means we are outside of parentheses
				if (t.getClass().isInstance(type) && tokenAlphabet.contains(t) && openParentheses == 0) 		
					return Optional.of(i);
			}
		}
		return Optional.empty();
	}
  
}
