package com.github.imbelo.calcolatricegrafica.model.factories;

import com.github.imbelo.calcolatricegrafica.model.parser.TokenFinder;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.token.*;

import java.util.List;
import java.util.Optional;


public class TokenTreeFactory implements TreeFactory<Token>{
  private TokenFinder finder;
    
  public TokenTreeFactory() {
  }
  public TokenTreeFactory(TokenFinder finder) {
    this.finder = finder;
  }
  @Override
	public Optional<Token> createTree(List<Token> tokens) {
		Optional<Integer> location = Optional.empty();	// index of the token we are searching for
		if(tokens == null || tokens.contains(null)){
			return Optional.empty();	
    }
		location = finder.find(tokens);
    if(!location.isPresent())
      return Optional.empty();
    Token token = tokens.get(location.get());
    if(token instanceof BinaryFunction type){
      List<Token> left = tokens.subList(0, location.get());
      List<Token> right = tokens.subList(location.get() + 1, tokens.size());
      var leftTree = createTree(left);
      var rightTree = createTree(right);
      if(leftTree.isPresent() && rightTree.isPresent())
        return TokenNodeFactory.createToken(leftTree.get(),rightTree.get(),type);
    }
    if(token instanceof UnaryFunction type){
      List<Token> inside = tokens.subList(1, tokens.size());
      var subTree = createTree(inside);
      if(subTree.isPresent())
        return TokenNodeFactory.createToken(subTree.get(),type);

    }
    if(token instanceof Variable){
      return Optional.ofNullable(TypeRegistry.create(token.getData()));
    }
    boolean inParentheses = (tokens.size() >= 2 && tokens.get(tokens.size() - 1) instanceof ClosedParentheses 
                && tokens.get(0) instanceof OpenParentheses);
    // list of token inside parentheses
    // node creation
    if(inParentheses){
      List<Token> inside = tokens.subList(1, tokens.size()-1);
      return createTree(inside);
    }

    location = finder.scanFromRight(tokens,new MyNumber(0.0));
		if (location.isPresent()) {
			// creation of number
			return Optional.of(new MyNumber(tokens.get(location.get()).getValue()));
		}
    return Optional.empty();
  }
  public void setTokenFinder(TokenFinder tokenFinder){
    this.finder = tokenFinder;
  }
}
