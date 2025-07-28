package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;

import java.util.function.*;


public class TokenType<T extends Token> implements Type<T>{
	private String data;
	private int priority;
  private Supplier<T> supplier;
	public TokenType() {
	}
	public TokenType(String string,Supplier<T> supplier,int priority) {
		this.data = string;	
    this.supplier = supplier;
		this.priority = priority;
	}
  @Override
  public T create(){
    return this.supplier.get();
  }
  public static Type<BinaryFunction> SUM(){
    return new TokenType<>("+",Sum::new,1);
  }
  public static Type<BinaryFunction> DIFFERENCE(){
    return new TokenType<>("-",Difference::new,2);
  }
  public static Type<BinaryFunction> MULTIPLY(){
    return new TokenType<>("*",Product::new,3);
  }
  public static Type<BinaryFunction> DIVIDE(){
    return new TokenType<>("/",Division::new,4);
  }
  public static Type<BinaryFunction> POW(){
    return new TokenType<>("^",Pow::new,5);
  }
  public static Type<UnaryFunction> SIN(){
    return new TokenType<>("sin",Sin::new,6);
  }
  public static Type<UnaryFunction> COS(){
    return new TokenType<>("cos",Cos::new,7);
  }
  public static Type<UnaryFunction> TAN(){
    return new TokenType<>("tan",Tan::new,8);
  }
  public static Type<UnaryFunction> LOG(){
    return new TokenType<>("log",Log::new,9);
  }
  public static Type<Variable> X(){
    return new TokenType<>("x",VariableX::getInstance,11);
  }
  public static Type<Variable> Y(){
    return new TokenType<>("y",VariableY::getInstance,12);
  }
  public static Type<MyNumber> NUMBER(){
    return new TokenType<>("" ,()->new MyNumber(0.0),13);
  }
  public static Type<AbstractParentheses> OPEN_PARENTHESES(){
    return new TokenType<>("(",OpenParentheses::new,14);
  }
  public static Type<AbstractParentheses> CLOSED_PARENTHESES(){
    return new TokenType<>(")",ClosedParentheses::new,15);
  }
	public int getPriority() {
		return this.priority;
	}
	@Override
	public String getData() {
		return this.data;
	}

}
