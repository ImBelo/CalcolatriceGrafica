package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Comparator;

public interface Token extends Node<Token>, Valuable, Ordered<Token>{
  String getData();
  @Override
  default Comparator<Token> getComparator() {
    return Comparator.comparingInt(Token::getPriority);
  }
}

