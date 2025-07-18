package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Optional;

public interface Parser<T> {
	public Optional<Tree<Node<T>>> parse(Expression expr);
}
