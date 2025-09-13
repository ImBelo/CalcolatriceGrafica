package com.github.imbelo.calcolatricegrafica.model.parser;

import com.github.imbelo.calcolatricegrafica.model.interfaces.ErrorFinder;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Expression;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Lexer;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.TokenExtractor;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Alphabet;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.github.imbelo.calcolatricegrafica.model.token.*;


public class LexerImpl implements Lexer{
  private TokenExtractor extractor;
  private ErrorFinder<SyntaxError,Token> errorFinder;
  private Alphabet<Token> alphabet;
  private String error;
 
  private LexerImpl(Builder builder) {
    this.extractor = builder.extractor;
    this.errorFinder = builder.errorFinder;
    this.alphabet = builder.alphabet;
        
    // Initialization logic
    this.extractor.setAlphabet(alphabet);
    this.errorFinder.setAlphabet(alphabet);
  }
  public static class Builder {
    private TokenExtractor extractor;
    private ErrorFinder<SyntaxError, Token> errorFinder;
    private AlphabetToken alphabet;

    public Builder() {
    }

    public Builder extractor(TokenExtractor extractor) {
      this.extractor = extractor;
      return this;
    }

    public Builder errorFinder(ErrorFinder<SyntaxError, Token> errorFinder) {
      this.errorFinder = errorFinder;
      return this;
    }

    public Builder alphabet(AlphabetToken alphabet) {
      this.alphabet = alphabet;
      return this;
    }
    public LexerImpl build() {
      Objects.requireNonNull(extractor, "extractor cannot be null");
      Objects.requireNonNull(errorFinder, "errorFinder cannot be null");
      Objects.requireNonNull(alphabet, "alphabet cannot be null");

      return new LexerImpl(this);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  public Optional<List<Token>>tokenize(Expression expr){
    List<Token> tokens;
    if(expr != null)
      tokens = new ArrayList<>();
    else
    return Optional.empty(); 
    // create shallow copy 
    Expression exprLeft = ExpressionImpl.of(expr.get());
    var errors = errorFinder.check(expr);
    if(errors.isPresent()){
        this.error = errors.get().getMessage();
        return Optional.empty();
    }

    while(!exprLeft.isEmpty()) {
      extractor.extract(exprLeft).ifPresent(tokens::add);
    }
    return Optional.ofNullable(tokens);
  }
  @Override
  public String getError(){
    return this.error;
  }


}
