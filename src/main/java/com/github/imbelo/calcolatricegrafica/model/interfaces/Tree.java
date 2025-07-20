package com.github.imbelo.calcolatricegrafica.model.interfaces;

public interface Tree<T extends Node>{
    T getRoot();
    void setRoot(T t);
}
