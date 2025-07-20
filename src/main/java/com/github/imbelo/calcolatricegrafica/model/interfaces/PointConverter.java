package com.github.imbelo.calcolatricegrafica.model.interfaces;

public interface PointConverter {
    CartesianPoint toCartesian(ScreenPoint screenPoint);
    ScreenPoint toScreen(CartesianPoint cartesianPoint);
}
