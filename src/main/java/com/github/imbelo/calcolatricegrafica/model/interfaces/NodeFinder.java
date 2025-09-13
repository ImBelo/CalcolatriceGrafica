package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Optional;
import java.util.List;

public interface NodeFinder<T extends Node<T>> {
  Optional<Integer> find(List<T> list);
}
