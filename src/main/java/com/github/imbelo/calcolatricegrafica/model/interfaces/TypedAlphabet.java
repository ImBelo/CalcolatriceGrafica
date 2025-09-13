package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.List;

public interface TypedAlphabet<T> extends Alphabet<T>{

  <S extends T> List<S> getSymbolsOfType(Class<S> type);

}
