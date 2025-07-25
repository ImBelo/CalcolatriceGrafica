package com.github.imbelo.calcolatricegrafica.model.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class PhaseResult<T,E> {
    protected T result;
    protected List<E> errors = new ArrayList<>();
    protected boolean isValid = true;

    public void addError(E error) {
        this.errors.add(error);
        this.isValid = false;
    }

    public void setResult(T result) {
        this.result = result;
    }

    // Getters
    public Optional<T> getResult() {
        return Optional.ofNullable(result);
    }

    public Optional<List<E>> getErrors() {
        return Optional.ofNullable(errors);
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
