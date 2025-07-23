package com.github.imbelo.calcolatricegrafica.model.interfaces;

public interface Camera {
	void zoom(double scale,CartesianPoint center);
	void pan(CartesianPoint vector);
	double getCameraHeight();
	double getCameraWidth();
	void setCameraHeight(double h);
	void setCameraWidth(double w);
	int getHeightPixels();
	int getWidthPixels();
	double getScale();
	CartesianPoint getCenter();
	void setCenter(CartesianPoint center);


}
