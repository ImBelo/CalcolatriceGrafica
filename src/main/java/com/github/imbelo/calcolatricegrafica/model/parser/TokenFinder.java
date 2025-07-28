package com.github.imbelo.calcolatricegrafica.model.parser;


import com.github.imbelo.calcolatricegrafica.model.factories.TokenNodeFactory;
import com.github.imbelo.calcolatricegrafica.model.interfaces.NodeFinder;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.TreeFactory;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;
import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;
import com.github.imbelo.calcolatricegrafica.model.token.UnaryFunction;

import java.util.Optional;
import java.util.List;
import com.github.imbelo.calcolatricegrafica.model.token.*;


public class TokenFinder implements NodeFinder<Token>{
  private AlphabetToken alphabet;

  public TokenFinder(AlphabetToken alphabet){
    this.alphabet = alphabet;
  }
  @Override
  public Optional<Token> find(List<Token> tokens,TreeFactory<Token> tokenTreeFactory){
    Optional<Integer> location;
    Optional<Token> found;
    for (Type<? extends Token> type : alphabet.getSymbolsInPriorityOrder()) {
      location = scanFromRight(tokens, type);
      if(location.isEmpty())
        continue;
      else{
        found = Optional.of(tokens.get(location.get()));
      } 
      Token token = found.get();
      if(token instanceof BinaryFunction){
        List<Token> left = tokens.subList(0, location.get());
				List<Token> right = tokens.subList(location.get() + 1, tokens.size());
        var leftTree = tokenTreeFactory.createTree(left);
        var rightTree = tokenTreeFactory.createTree(right);
        if(leftTree.isPresent() && rightTree.isPresent())
			    return TokenNodeFactory.createToken(leftTree.get(),rightTree.get(),type);
      }
      if(token instanceof UnaryFunction){
        List<Token> inside = tokens.subList(1, tokens.size());
        var subTree = tokenTreeFactory.createTree(inside);
        if(subTree.isPresent())
          return TokenNodeFactory.createToken(subTree.get(),type);

			}
      if(token instanceof Variable)
        return Optional.ofNullable(type.create());
      boolean inParentheses = (tokens.size() >= 2 && tokens.get(tokens.size() - 1) instanceof ClosedParentheses 
								  && tokens.get(0) instanceof OpenParentheses);
      if (inParentheses) {
		  // list of token inside parentheses
			List<Token> inside = tokens.subList(1, tokens.size()-1);
			// node creation
				return tokenTreeFactory.createTree(inside);
		}
  }
    return Optional.empty();
  }


  public Optional<Integer> scanFromRight(List<Token> tokens,Type<? extends Token> type) {
		int openParentheses = 0;
    List<Type<? extends Token>> tokenAlphabet = this.alphabet.getSymbols();
    if(!tokenAlphabet.contains(type))
      return Optional.empty();
		for (int i = tokens.size()-1; i >= 0;i--) {
      Token t = tokens.get(i);
      Type<? extends Token> tokenType = t.getType();
			if (t != null) {
				if (t instanceof ClosedParentheses) 
					openParentheses--;
				else if (t instanceof OpenParentheses) 
					openParentheses++;
					// if openParentheses is 0 it means we are outside of parentheses
				if (type.equals(tokenType) && tokenAlphabet.contains(tokenType) && openParentheses == 0) 		
					return Optional.of(i);
			}
		}
		return Optional.empty();
	}
  
}
