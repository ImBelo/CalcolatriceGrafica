package com.github.imbelo.calcolatricegrafica.model.interfaces;

public interface Token extends Node<Token>, Valuable{
  String getData();
  Type<? extends Token> getType();
}

