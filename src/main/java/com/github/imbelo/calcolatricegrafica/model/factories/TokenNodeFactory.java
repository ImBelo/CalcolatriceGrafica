package com.github.imbelo.calcolatricegrafica.model.factories;

import java.util.List;
import java.util.Optional;

import com.github.imbelo.calcolatricegrafica.model.token.BinaryFunction;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;
import com.github.imbelo.calcolatricegrafica.model.token.UnaryFunction;

public interface TokenNodeFactory {
	
	public static Optional<Token> createToken(Token left,Token right,BinaryFunction type) {
		List<BinaryFunction> operations = new AlphabetToken().getOperations();
		if(!operations.contains(type) || right == null || left == null)
			return Optional.empty(); 
		type.setLeft(left);
		type.setRight(right);
		return Optional.of(type); 
	}
	public static Optional<Token> createToken(Token child,UnaryFunction type) {
		List<UnaryFunction> functions = new AlphabetToken().getFunctions();
		if(!functions.contains(type) || child == null)
			return Optional.empty();
		type.setChild(child);
		return Optional.of(type);
		
	}

}
