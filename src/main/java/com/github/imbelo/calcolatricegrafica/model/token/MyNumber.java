package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Node;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

import java.util.List;

public class MyNumber extends AbstractToken {
	private double num;
	public MyNumber(double num,String data){
    super(data);
		this.setNum(num);
	}
  public MyNumber(String data){
    super(data);
  }
  public MyNumber(double data){
    super("");
    setNum(data);
  }
	public void setNum(double num) {
		this.num = num; 
	}
	@Override
	public double getValue() {
		return this.num;
	}
  @Override
  public int getPriority() {
    return 12;
  }
	
	@Override
	public boolean equals(Object obj) {
    return true;
  }
  

	@Override
	public List<Node<Token>> getChildrens() {
		return null;
	}

	@Override
	public void addChild(Node<Token> child) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeChild(Node<Token> child) {
		throw new UnsupportedOperationException();
	}
}
