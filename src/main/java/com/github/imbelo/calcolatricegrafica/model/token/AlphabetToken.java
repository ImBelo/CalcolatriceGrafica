package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class AlphabetToken implements OrderedAlphabet<Type<? extends Token>> {
  private static List<Type<UnaryFunction>> functions = List.of(TokenType.SIN(),TokenType.COS(),TokenType.TAN(),TokenType.LOG());
  private static List<Type<BinaryFunction>> operations = List.of(TokenType.SUM(),TokenType.DIVIDE(),TokenType.MULTIPLY(),TokenType.DIFFERENCE());
  private static List<Type<Variable>> variables = List.of(TokenType.X(),TokenType.Y());
  private static List<Type<? extends Token>> other = List.of(TokenType.NUMBER(),TokenType.CLOSED_PARENTHESES(),TokenType.OPEN_PARENTHESES());

  public static List<Type<BinaryFunction>> getOperations(){
    return operations;
  }

  public static List<Type<UnaryFunction>> getFunctions(){
    return functions;
  }

  public static List<Type<Variable>> getVariables(){
    return variables;
  }

  public void addFunction(Type<UnaryFunction> function) {
    functions.add(function);
  }

  public void addOperation(Type<BinaryFunction> operation) {
    operations.add(operation);
  }

  public void addVariable(Type<Variable> variable) {
    variables.add(variable);
  }
  // we also get symbols in priority order for free
  @Override
  public List<Type<? extends Token>> getSymbols() {
    return Stream.of(operations, functions, variables,other)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
  }

}
