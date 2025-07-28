package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Node;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

import java.util.List;

public abstract class AbstractParentheses extends AbstractToken {
	@Override
	public double getValue()  {
		return 0;
	}
  public AbstractParentheses(Type<? extends AbstractParentheses> type){
    super(type);
  }

	@Override
	public List<Node<Token>> getChildrens() {
		throw new UnsupportedOperationException();
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
