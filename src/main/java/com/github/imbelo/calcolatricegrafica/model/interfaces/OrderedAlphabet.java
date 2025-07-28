package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Comparator;
import java.util.List;

public interface OrderedAlphabet<T extends Type<?>> extends Alphabet<T>{

  default List<T> getSymbolsInPriorityOrder() {
    return getSymbolsOrderedBy(Comparator.comparingInt(T::getPriority));
    }
  default List<T> getSymbolsOrderedBy(Comparator<? super T> comparator) {
    return getSymbols().stream().sorted(comparator).toList();
  }

}
