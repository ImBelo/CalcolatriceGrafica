package com.github.imbelo.calcolatricegrafica.model.interfaces;

public interface Token extends Node<Token>, Valuable{
  Type getType();
}
