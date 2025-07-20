package com.github.imbelo.calcolatricegrafica.model.interfaces;

import com.github.imbelo.calcolatricegrafica.model.main.CartesianPointImpl;
import com.github.imbelo.calcolatricegrafica.model.main.ScreenPointImpl;

public interface PointConverter {
    CartesianPoint toCartesian(ScreenPoint screenPoint);
    ScreenPoint toScreen(CartesianPoint cartesianPoint);
}
