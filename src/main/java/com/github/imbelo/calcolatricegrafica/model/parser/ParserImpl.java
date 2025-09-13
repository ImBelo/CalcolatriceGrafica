package com.github.imbelo.calcolatricegrafica.model.parser;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;

import java.util.List;
import java.util.Optional;

public class ParserImpl implements Parser<Function>{
  private Lexer lexer;
	private List<Token> tokens;
	private AlphabetToken alphabet; 
	private ErrorFinder<SemanticError,Token> errorFinderSemantic;
  private TreeFactory<Token> treeFactory;
  private String error;
  private ParserImpl(Builder builder){
    this.lexer = builder.lexer;
    this.alphabet = builder.alphabet;
    this.errorFinderSemantic = builder.errorFinderSemantic;
    this.treeFactory = builder.treeFactory;
    this.alphabet = builder.alphabet;
    errorFinderSemantic.setAlphabet(alphabet);
  }

  public Optional<Function> parse(Expression expr){
    if (expr == null){
      return Optional.empty();
    }
    Optional<List<Token>> previousresult = lexer.tokenize(expr);
    if(previousresult.isEmpty()){
      error = lexer.getError();
      return Optional.empty();
    }
    this.tokens = previousresult.get();
    // Search for semantic Error
    var errors = errorFinderSemantic.check(tokens);
    if(errors.isPresent()){
      error = errors.get().getMessage();
      return Optional.empty();
    }
		var root = treeFactory.createTree(tokens);
    if(root.isPresent()){
      return Optional.of(new FunctionImpl(root.get(),alphabet.getVariables()));
    }
     return Optional.empty();
  }
  public static Builder builder(){
    return new Builder();
  }

  public static class Builder {
    private Lexer lexer;
    private ErrorFinder<SemanticError,Token> errorFinderSemantic;
    private TreeFactory<Token> treeFactory;
	  private AlphabetToken alphabet;
    private TokenFinder tokenFinder;

    public Builder(){
    }

    public Builder treeFactory(TreeFactory<Token> treeFactory){
      this.treeFactory = treeFactory;
      return this;
    }
    public Builder errorFinderSemantic(ErrorFinder<SemanticError,Token> errorFinderSemantic) {
      this.errorFinderSemantic = errorFinderSemantic;// Defensive copy
      return this;

    }
    public Builder lexer(Lexer lexer){
      this.lexer = lexer;
      return this;
    }
    public Builder tokenFinder(TokenFinder tokenFinder){
      this.tokenFinder = tokenFinder;
      return this;
    }

    public Builder alphabet(AlphabetToken alphabet) {
      this.alphabet = alphabet;// Defensive copy
      return this;
    }

    // Build method
    public ParserImpl build() {
      return new ParserImpl(this);
    }  
  }
  public boolean hasError(){
    return this.error != null;
  }
  public String getError(){
    return this.error;
  }
}
