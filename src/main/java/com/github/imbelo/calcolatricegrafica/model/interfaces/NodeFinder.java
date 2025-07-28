package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Optional;
import java.util.List;

public interface NodeFinder<T extends Node<T>> {
  Optional<T> find(List<T> list, TreeFactory<T> treeFactory);
}
