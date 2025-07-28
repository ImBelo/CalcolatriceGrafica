package com.github.imbelo.calcolatricegrafica.model.factories;

import java.util.List;
import java.util.Optional;

import com.github.imbelo.calcolatricegrafica.model.token.BinaryFunction;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.token.AlphabetToken;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;
import com.github.imbelo.calcolatricegrafica.model.token.UnaryFunction;

public interface TokenNodeFactory {
	
	public static Optional<Token> createToken(Token left,Token right,Type<? extends Token> type) {
		List<Type<BinaryFunction>> operations = AlphabetToken.getOperations();
		if(!operations.contains(type) || right == null || left == null)
			return Optional.empty();
		BinaryFunction token = (BinaryFunction)type.create();
		token.setLeft(left);
		token.setRight(right);
		return Optional.of(token); 
	}
	public static Optional<Token> createToken(Token child,Type<? extends Token> type) {
		List<Type<UnaryFunction>> functions = AlphabetToken.getFunctions();
		if(!functions.contains(type) || child == null)
			return Optional.empty();
		UnaryFunction token = (UnaryFunction)type.create();
		token.setChild(child);
		return Optional.of(token);
		
	}

}
