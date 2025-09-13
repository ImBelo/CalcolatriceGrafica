package com.github.imbelo.calcolatricegrafica.model.parser;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;
import com.github.imbelo.calcolatricegrafica.model.token.BinaryFunction;
import com.github.imbelo.calcolatricegrafica.model.token.UnaryFunction;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;

public class ErrorFinderSyntax implements ErrorFinder<SyntaxError,Token>{
  private TypedAlphabet<Token>alphabet;
  private String error;
  public ErrorFinderSyntax(TypedAlphabet<Token> alphabet){
  }
  public ErrorFinderSyntax(){
  }

  @Override
  public void setAlphabet(Alphabet<Token> alphabet) {
    if (!(alphabet instanceof TypedAlphabet)) {
      throw new IllegalArgumentException("Requires TypedAlphabet");
    }
    this.alphabet = (TypedAlphabet<Token>) alphabet;
  }

  @Override
  public Optional<SyntaxError> check(Expression input) {
    if(alphabet == null)
      throw new UnsupportedOperationException("ErrorFinder Requires an Alphabet");
    Optional<SyntaxError> error = Optional.empty();
    if (input == null || input.get() == null)
    return Optional.of(new SyntaxError("Expression is null",0,0));
    removeSpaces(input);
    error = checkFunctions(input);
    if(error.isPresent())
    return error;
    error = checkParentheses(input);
    if(error.isPresent())
    return error;
    error = checkNumber(input);
    if(error.isPresent())
      return error;
    error = checkIllegalChar(input);
    if(error.isPresent())
    return error;
    return Optional.empty(); 

  }
  @Override
  public Optional<SyntaxError> check(List<Token> tokens) {
    throw new UnsupportedOperationException();
  }
  public static void removeSpaces(Expression expr)  {
    if(expr == null || expr.get() == null) 
    return;

    String content = expr.get();
    Pattern pattern = Pattern.compile(" ");
    Matcher matcher = pattern.matcher(content);

    if (matcher.find()) {
      content = content.replaceAll(" ", "");
      expr.set(content);  	
    }

  }
  public Optional<SyntaxError> checkFunctions(Expression expr)  {
    String content = expr.get();
    Pattern pattern = Pattern.compile("[a-wA-W]+");
    Matcher matcher = pattern.matcher(content);
    boolean exist = true;
    while (matcher.find())
  {	
      String name= "";
    exist = false;
      for(var function: alphabet.getSymbolsOfType(UnaryFunction.class)) {
      name = matcher.group();
        if(name.equalsIgnoreCase(function.getData())){
          exist = true;
        }
      }
      if (!exist) 
      return Optional.of(new SyntaxError("No such function: " + name + " exists",matcher.start(),0));
    }
    return Optional.empty();	
  }
  public Optional<SyntaxError> checkParentheses(Expression expr)  {
    String content = expr.get();
    int counter = 0;
    int i;
    for (i = 0;i < content.length();i++) {
      if(content.charAt(i) == '(')
        counter++;
      if (content.charAt(i) == ')')
        counter--;
    }
    if(counter != 0) {
      return Optional.of(new SyntaxError("Wrong parentheses",i ,0));
    }
    return Optional.empty();

  }
  public static Optional<SyntaxError> checkIllegalChar(Expression expr) {
    String content = expr.get();
    // not allowed characters
    Pattern pattern = Pattern.compile("[^A-Za-z0-9|+|\\-|^|/|*\\(|)|.]");
    Matcher matcher = pattern.matcher(content);   
    if (matcher.find()){
      return Optional.of(new SyntaxError("Symbol not in alphabet",matcher.start(),0));
    }
    return Optional.empty();
  }
  public static Optional<SyntaxError> checkNumber(Expression expr)  {
    String content = expr.get();
    int comma = 0;
    int i;
    for (i = 0; i < content.length();i++) {	
      boolean isDigit = Character.isDigit(content.charAt(i));
      boolean isComma = content.charAt(i) == '.';
      comma = 0;
      if(isDigit) {
        while(isDigit | isComma && i < content.length() ) {
          isDigit = Character.isDigit(content.charAt(i));
          isComma = content.charAt(i) == '.';
          if(isComma) {
            comma++;
          }
          i++;
        }
      }
      if(comma!=0 && comma != 1) 
        return Optional.of(new SyntaxError("Number not formed correctly",i,0));
    }
    return Optional.empty();
}



}
