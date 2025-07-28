package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.List;
import java.util.Optional;

public interface TreeFactory<T extends Node<T>>{
  Optional<T> createTree(List<T> list);
}
