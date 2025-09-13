package com.github.imbelo.calcolatricegrafica.model.parser;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;
import com.github.imbelo.calcolatricegrafica.model.token.BinaryFunction;
import com.github.imbelo.calcolatricegrafica.model.token.UnaryFunction;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.token.*;
import com.github.imbelo.calcolatricegrafica.model.parser.*;

public class ErrorFinderSemantic implements ErrorFinder<SemanticError,Token>{
  private OrderedAlphabet<Token> alphabet;
  public ErrorFinderSemantic(OrderedAlphabet<Token> alphabet){
    this.alphabet = alphabet;
  }
  public ErrorFinderSemantic(){
  }

  @Override
  public void setAlphabet(Alphabet<Token> alphabet) {
    if (!(alphabet instanceof TypedAlphabet)) {
      throw new IllegalArgumentException("Requires TypedAlphabet");
    }
    this.alphabet = (OrderedAlphabet<Token>) alphabet;
  }
  @Override
  public Optional<SemanticError> check(Expression expr) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<SemanticError> check(List<Token> tokens) {
    if(alphabet == null)
      throw new UnsupportedOperationException("ErrorFinder Requires an Alphabet");
    Optional<SyntaxError> error = Optional.empty();
    if (tokens== null || tokens.contains(null)){
      return Optional.of(new SemanticError("Expression is null",0,0,""));
    }
    Optional<SemanticError> binaryError = checkBinaryOperations(tokens);
    if (binaryError.isPresent()) {
      return binaryError;
    }
    return Optional.empty(); 

  }
  private Optional<SemanticError> checkBinaryOperations(List<Token> tokens) {
    for (int i = 0; i < tokens.size(); i++) {
      Token token = tokens.get(i);

      if (token instanceof BinaryFunction) {
        BinaryFunction binaryOp = (BinaryFunction) token;

        // Check if binary operator has left operand
        if (i == 0) {
          return Optional.of(new SemanticError(
            "Missing left operand for binary operator: " + binaryOp.toString(),
            i, i, binaryOp.toString()
          ));
        }

        // Check if binary operator has right operand
        if (i == tokens.size() - 1) {
          return Optional.of(new SemanticError(
            "Missing right operand for binary operator: " + binaryOp.toString(),
            i, i, binaryOp.toString()
          ));
        }

        Token left = tokens.get(i - 1);
        Token right = tokens.get(i + 1);

        // Check if left operand is valid (not another operator unless it's a function result)
        if (left instanceof BinaryFunction || left instanceof UnaryFunction) {
          // Special case: if left is a unary function, it might be valid
          if (!(left instanceof UnaryFunction)) {
            return Optional.of(new SemanticError(
                            "Invalid left operand for binary operator: " + binaryOp.toString(),
              i - 1, i - 1, left.toString()
            ));
          }
        }

        // Check if right operand is valid
        if (right instanceof BinaryFunction) {
          return Optional.of(new SemanticError(
            "Invalid right operand for binary operator: " + binaryOp.toString(),

            i + 1, i + 1, right.toString()
          ));
        }

      }
    }
    return Optional.empty();
  }

}

