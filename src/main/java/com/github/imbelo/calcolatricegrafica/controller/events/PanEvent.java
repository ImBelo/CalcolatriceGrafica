package com.github.imbelo.calcolatricegrafica.controller.events;

import com.github.imbelo.calcolatricegrafica.model.interfaces.CartesianPoint;

import java.util.Optional;

public class PanEvent implements ViewEvent {
    private CartesianPoint vector;
    public PanEvent(CartesianPoint vector){
        this.vector = vector;
    }
    public Optional<CartesianPoint> getVector(){
        return Optional.of(this.vector);
    }
}
