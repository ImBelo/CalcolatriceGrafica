package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Node;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

import java.util.List;

public class MyNumber extends AbstractToken {
	private double num;
	public MyNumber(double num){
    super(TokenType.NUMBER());
		this.setNum(num);
	}
	public MyNumber(){
    super(TokenType.NUMBER());
	}
  public MyNumber(Type<Variable> type){
    super(type);
  }
	public void setNum(double num) {
		this.num = num; 
	}
	@Override
	public double getValue() {
		return this.num;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
		if (!(obj instanceof MyNumber otherNumber))
            return false;
        return this.num == otherNumber.getValue();
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
