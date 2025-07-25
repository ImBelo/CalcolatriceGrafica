package com.github.imbelo.calcolatricegrafica.model.parser;


import com.github.imbelo.calcolatricegrafica.model.interfaces.NodeFinder;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.OrderedAlphabet;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

import java.util.function.Predicate;
import java.util.Optional;
import java.util.List;


public class TokenFinder implements NodeFinder<Token>{
  private static OrderedAlphabet<Type> alphabet;

  public TokenFinder(OrderedAlphabet<Type> alphabet){
    this.alphabet = alphabet;
  }
  @Override
  public Optional<Token> find(List<Token> tokens){
    Optional location;
    for (Type type : alphabet.getSymbolsInPriorityOrder()) {
            Predicate<Type> criteria = createPredicate(type);
            location = scanFromRight(tokens, criteria);
            if(location.isEmpty())
              continue;
            else{
              token = tokens.get(location.get());
            }

            
            if (location != -1) {
                return handleFoundOperator(tokens, location);
            }
        }

  }
  private static Predicate<Type> createPredicate(Type type) {
    return t -> t.equals(type) && 
      alphabet.getSymbols().contains(t);
    }


  public static Optional<Integer> scanFromRight(List<Token> tokens, Predicate<Type> criteria) {
		int openParentheses = 0;
		for (int i = tokens.size()-1; i >= 0;i--) {
			Type t = tokens.get(i).getType() ;
			if (t != null) {
				if (t  == TokenType.CLOSED_PARENTHESES) 
					openParentheses--;
				else if (t == TokenType.OPEN_PARENTHESES) 
					openParentheses++;
					// if openParentheses is 0 it means we are outside of parentheses
				if (criteria.test(t) && openParentheses == 0) 		
					return Optional.of(i);
			}
		}
		return Optional.empty();
	}
  
}
