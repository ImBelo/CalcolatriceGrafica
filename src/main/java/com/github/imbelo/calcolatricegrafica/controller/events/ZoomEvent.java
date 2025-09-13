package com.github.imbelo.calcolatricegrafica.controller.events;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Camera;
import com.github.imbelo.calcolatricegrafica.model.interfaces.CartesianPoint;

import java.util.Optional;

public class ZoomEvent implements ViewEvent {
    private double scale;
    private CartesianPoint focus;
    private Camera camera;
    // Evento View -> Controller
    public ZoomEvent(double scale, CartesianPoint focus){
        this.scale = scale;
        this.focus = focus;
    }
    // Evento Controller -> Model
    public ZoomEvent(Camera camera){
        this.camera = camera;
    }
    public Optional<CartesianPoint> getFocus(){
        return Optional.ofNullable(this.focus);
    }
    public Optional<Double> getScale(){
        return Optional.ofNullable(this.scale);
    }
    public Optional<Camera> getCamera(){
        return Optional.ofNullable(camera);
    }

}
