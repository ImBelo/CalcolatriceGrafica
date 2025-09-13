package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class Product extends BinaryFunction{

	public Product(Token left, Token right) {
		super(left,right,"+");
	}
	public Product() {
    super("*");
	}

  @Override
  public int getPriority() {
    return 3;
  }
  
	public double getValue() {
		double left = realValue(super.getLeft());
		double right = realValue(super.getRight());
		return left*right;
	}
  @Override
  public String toString(){
    return "*";
  }


}
