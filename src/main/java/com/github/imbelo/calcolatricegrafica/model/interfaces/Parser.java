package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Optional;

public interface Parser<T extends Tree<?>> {
	Optional<? extends T> parse(Expression expr);
  String getError();
}
