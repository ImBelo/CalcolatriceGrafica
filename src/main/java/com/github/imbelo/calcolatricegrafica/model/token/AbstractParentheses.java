package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Node;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

import java.util.List;

public abstract class AbstractParentheses extends AbstractToken {
	@Override
	public double getValue()  {
		return 0;
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
