package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.Comparator;

public interface Ordered<T> {
    int getPriority();
    Comparator<T> getComparator();
}
