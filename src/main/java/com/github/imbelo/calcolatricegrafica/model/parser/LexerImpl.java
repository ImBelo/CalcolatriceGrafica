package com.github.imbelo.calcolatricegrafica.model.parser;

import com.github.imbelo.calcolatricegrafica.model.interfaces.ErrorFinder;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Expression;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Lexer;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.TokenExtractor;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LexerImpl implements Lexer{
  private TokenExtractor extractor;
  private ErrorFinder<SyntaxError> errorFinder;

  public LexerImpl(TokenExtractor extractor, ErrorFinder<SyntaxError> errorFinder){
    this.errorFinder = Objects.requireNonNull(errorFinder);
    this.extractor = Objects.requireNonNull(extractor);

  }

  public TokenizationResult tokenize(Expression expr){
    TokenizationResult result = new TokenizationResult();
		List<Token> tokens = new ArrayList<>();
	  if(expr != null)
      tokens = new ArrayList<>();
    else
      Optional.empty(); 
		// create shallow copy 
		Expression exprLeft = ExpressionImpl.of(expr.get());
		
		errorFinder.check(expr).ifPresent(result::addError);		

		while(!exprLeft.isEmpty() && result.isValid()) {
			extractor.extract(exprLeft).ifPresent(tokens::add);
		}
    result.setResult(tokens);
		return result;
	}

  
}
