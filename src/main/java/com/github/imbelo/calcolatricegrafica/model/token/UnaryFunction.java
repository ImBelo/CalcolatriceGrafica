package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Node;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

import java.util.List;
import java.util.NoSuchElementException;

public class UnaryFunction extends AbstractToken {
	private Token child;
	public UnaryFunction(Token child,Type<UnaryFunction> type) {
    super(type);
    this.child = child;
	}
	public UnaryFunction(Type<UnaryFunction> type) {
    super(type);
	}

	public double getValue() {
		return 0;
	}
	
	public Token getChild() {
		return child;
	} 

	public void setChild(Token child) {
		this.child = child;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (!(obj instanceof UnaryFunction otherToken))
            return false;
        if(this.child == null && otherToken.getChild() == null)
			return true;
		if(this.child == null || otherToken.getChild() == null)
			return false;
        return this.child.equals(otherToken.getChild());


    }
	@Override
	public String getData() {
		return super.getData();
	}


	@Override
	public List<Node<Token>> getChildrens() {
		return List.of(child);
	}

	@Override
	public void addChild(Node<Token> child) {
		if(this.child != null)
			this.child = (Token) child;
		else{
			throw new UnsupportedOperationException("Can only have 1 children");
		}
	}

	@Override
	public void removeChild(Node<Token> child) {
		if(this.child == child)
			this.child = null;
		else{
			throw new NoSuchElementException();
		}
	}
}
