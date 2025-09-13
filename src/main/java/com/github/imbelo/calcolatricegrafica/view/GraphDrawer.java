package com.github.imbelo.calcolatricegrafica.view;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Camera;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Graph;

import java.awt.Image;
import java.util.List;

public interface GraphDrawer {
	void drawBackground();
	void drawAxis();
	void drawFunction();
	void drawNumbers();
	Image draw();
    void setCamera(Camera camera);
    void setGraphs(List<Graph> graphs);
}
