package com.github.imbelo.calcolatricegrafica.model.parser;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Expression;
import com.github.imbelo.calcolatricegrafica.model.token.Arity;

import java.util.function.Predicate;  
import java.util.function.Function;   

public class ExpressionImpl implements Expression{
  private final String expression;

  private ExpressionImpl(String expression) {
    this.expression = expression;
  }

  public static ExpressionImpl of(String expression) {
    return new ExpressionImpl(expression != null ? expression.trim() : "");
  }
  public static Expression empty() {
    return new ExpressionImpl("");
  }
  public Boolean isEmpty(){
    return this.expression.isEmpty();
  }

  public Arity getArity(){
    return this.expression.contains("y")?Arity.Binary:Arity.Unary;
  }

  public boolean isPresent() {
    return !expression.isEmpty();
  }

  public int length() {
    return expression.length();
  }

  public String get() {
    return expression;
  }

  public Expression map(Function<String, String> mapper) {
    return isPresent() ? ExpressionImpl.of(mapper.apply(expression)) : this;
  }

  public Expression filter(Predicate<String> predicate) {
    return isPresent() && predicate.test(expression) ? this : empty();
  }

  @Override
  public String toString() {
    return expression;
  }
}
