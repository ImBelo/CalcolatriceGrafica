package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Optional;

public interface Parser<T extends Node<T>> {
	public Optional<Tree<T>> parse(Expression expr);
}
