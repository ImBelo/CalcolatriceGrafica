package com.github.imbelo.calcolatricegrafica.model.interfaces;

public interface Type<T>{
  String getData();
  int getPriority();
  T create();
}
