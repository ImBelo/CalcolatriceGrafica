package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;

import java.util.*;
import java.util.stream.Collectors;

public class AlphabetToken implements OrderedAlphabet<Token>,TypedAlphabet<Token> {
private final List<Token> symbols;
  private final Map<Class<? extends Token>, List<Token>> categorizedSymbols;

  public AlphabetToken() {
    this.symbols = loadAllTokens();
    this.categorizedSymbols = Map.of(
      BinaryFunction.class, loadTokens(BinaryFunction.class),
      UnaryFunction.class, loadTokens(UnaryFunction.class),
      Variable.class, loadTokens(Variable.class)
    );
  }

  @Override
  public <T extends Token> List<T> getSymbolsOfType(Class<T> type) {
    return symbols.stream()
      .filter(type::isInstance)
      .map(type::cast)
      .collect(Collectors.toList());
  }
  private List<Token> loadAllTokens() {
    return TypeRegistry.getRegistry().entrySet().stream()
      .map(e -> TypeRegistry.create(e.getKey()))
      .sorted(Comparator.comparingInt(Token::getPriority))
      .collect(Collectors.toList());
  }

  private List<Token> loadTokens(Class<? extends Token> type) {
    return symbols.stream()
      .filter(type::isInstance)
      .collect(Collectors.toList());
  }


  public List<BinaryFunction> getOperations() {
    return getSymbolsOfType(BinaryFunction.class);
  }

  public List<UnaryFunction> getFunctions() {
    return getSymbolsOfType(UnaryFunction.class);
  }

  public List<Variable> getVariables() {
    return getSymbolsOfType(Variable.class);
  }

  @Override
  public List<Token> getSymbols() {
    return Collections.unmodifiableList(symbols);
  }

  // Dynamic addition
  public void addToken(Token token) {
    symbols.add(token);
    categorizedSymbols.computeIfPresent(token.getClass(), 
      (k, v) -> { v.add(token); return v; });
  }
}
