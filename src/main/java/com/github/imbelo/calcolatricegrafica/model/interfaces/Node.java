package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.List;

public interface Node<T>{
    List<Node<T>> getChildrens();
    void addChild(Node<T> child);
    void removeChild(Node<T> child);
}
