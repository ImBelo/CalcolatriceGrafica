package com.github.imbelo.calcolatricegrafica.model.token;


import com.github.imbelo.calcolatricegrafica.model.interfaces.Node;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;

import java.util.List;
import java.util.NoSuchElementException;

public class BinaryFunction extends AbstractToken{
	private Token right;
	private Token left;
	
	public BinaryFunction(Token left,Token right) {
		this.setLeft(left);
		this.setRight(right);
	}
	public BinaryFunction() {
	}
	public void setType(Type type) {
		super.setType(type);
		super.setData(type.getData());
	}
	public double getValue() {
		return 0;
	}
	public Token getRight() {
		return right;
	}

	public void setRight(Token right) {
		this.right = right;
	}

	public Token getLeft() {
		return left;
	}
	public void setLeft(Token left) {
		this.left = left;
	}
	protected void isWellFormed(String message) {
		if(getLeft() == null || getRight() == null)
			throw new NotWellFormedFormulaException(message);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (!(obj instanceof BinaryFunction otherToken))
            return false;
        if(this.left == null && this.right == null &&
    	otherToken.getLeft() == null && otherToken.getRight() == null)
    	 return true;
     if(this.left == null || this.right == null ||
    	otherToken.getLeft() == null || otherToken.getRight() == null)
    	    	 return false;
        return this.left.equals(otherToken.getLeft()) &&
                this.right.equals(otherToken.getRight());

    }
	@Override
	public List<Node<Token>> getChildrens() {
		return List.of(right,left);
	}
	@Override
	public void addChild(Node<Token> child) {
		if(right == null)
			right = (Token) child;
		else if(left == null)
			left = (Token) child;
		else
			throw new UnsupportedOperationException();

	}
	@Override
	public void removeChild(Node<Token> child) {
		if(right == child)
			right = null;
		if(left == child)
			left = null;
		else
			throw new NoSuchElementException();
	}
}
