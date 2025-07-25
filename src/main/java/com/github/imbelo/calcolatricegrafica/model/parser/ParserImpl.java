package com.github.imbelo.calcolatricegrafica.model.parser;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class ParserImpl implements Parser<Token>{
  private Lexer lexer;
	private List<Token> tokens;
	private OrderedAlphabet<Type> alphabet; 
	private ErrorFinder<CompilerError> errorFinderSemantic;
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
    if(previousresult.isValid())  
      previousresult.getErrors().get().forEach(result::addError);
      return result;
    // Search for semantic Error
    errorFinderSemantic.check(expr).ifPresent(result::addError);
    
		Token root = treeFactory.createTree(nodeFinder,tokens);
      

    result.setResult(new FunctionImpl(root));
		return result; 
  }

 public static class Builder {
    // Required fields (final)
    private final Lexer lexer;
    private final ErrorFinder<CompilerError> errorFinderSemantic;
    private final TokenExtractor tokenExtractor;
    private final TreeFactory<Token> treeFactory;
	  private final OrderedAlphabet<Type> alphabet;

    // Optional fields (with defaults)
    private List<Token> tokens = new ArrayList<>();
    private List<Token> variables = new ArrayList<>();

    public Builder(Lexer lexer, ErrorFinder<SemanticError> errorFinderSemantic) {
      this.lexer = Objects.requireNonNull(lexer, "Lexer must not be null");
      this.errorFinderSemantic = Objects.requireNonNull(errorFinderSemantic, "ErrorFinderSemantic must not be null");
    }


    public Builder errorFinderSemantic(ErrorFinderSemantic errorFinderSemantic) {
      this.errorFinderSemantic = errorFinderSemantic;// Defensive copy
      return this;

    }

    public Builder TokenTreeFactory(NodeExtractor nodeExtractor) {
      this.nodeExtractor = nodeExtractor;
      return this;
    }

    public Builder alphabet(Alphabet<Type> alphabet) {
      this.alphabet = alphabet;// Defensive copy
      return this;
    }

    // Build method
    public ParserImpl build() {
      return new ParserImpl(this);
    }  
  }

}
