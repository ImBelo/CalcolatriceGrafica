package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.List;

public interface TreeFactory<T extends Node<T>>{
  T createTree(NodeFinder<T> nodeFinder,List<T> list);
}
