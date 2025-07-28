package com.github.imbelo.calcolatricegrafica.model.parser;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class ParserImpl implements Parser<Token>{
  private Lexer lexer;
	private List<Token> tokens;
	private AlphabetToken alphabet; 
	private ErrorFinder<SemanticError> errorFinderSemantic;
  private NodeFinder<Token> nodeFinder;
  private TreeFactory<Token> treeFactory;
  private ParserImpl(Builder builder){
    this.lexer = builder.lexer;
    this.alphabet = builder.alphabet;
    this.errorFinderSemantic = builder.errorFinderSemantic;
    this.treeFactory = builder.treeFactory;
  }

  public ParserResult<Token> parse(Expression expr){
    ParserResult<Token> result = new ParserResult<>();
    var previousresult = lexer.tokenize(expr);
    if(!previousresult.isValid()){  
      previousresult.getErrors().get().forEach(result::addError);
      return result;
    }
    // Search for semantic Error
    errorFinderSemantic.check(expr).ifPresent(result::addError);
    
		var root = treeFactory.createTree(tokens);
      
    if(root.isPresent())
      result.setResult(new FunctionImpl(root.get(),AlphabetToken.getVariables()));
		return result; 
  }

 public static class Builder {
    // Required fields (final)
    private Lexer lexer;
    private ErrorFinder<SemanticError> errorFinderSemantic;
    private TreeFactory<Token> treeFactory;
	  private AlphabetToken alphabet;

    public Builder(Lexer lexer, ErrorFinder<SemanticError> errorFinderSemantic,AlphabetToken alphabet,TreeFactory<Token> treeFactory) {
      this.treeFactory = Objects.requireNonNull(treeFactory,"TreeFactory must not be null");
      this.alphabet = Objects.requireNonNull(alphabet,"Alphabet must not be null");
      this.lexer = Objects.requireNonNull(lexer, "Lexer must not be null");
      this.errorFinderSemantic = Objects.requireNonNull(errorFinderSemantic, "ErrorFinderSemantic must not be null");
    }


    public Builder errorFinderSemantic(ErrorFinder<SemanticError> errorFinderSemantic) {
      this.errorFinderSemantic = errorFinderSemantic;// Defensive copy
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

}
