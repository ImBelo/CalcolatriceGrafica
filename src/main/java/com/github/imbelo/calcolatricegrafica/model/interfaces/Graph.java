package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.List;


public interface Graph {
	static final int HEIGHT = 600;
	static final int WIDTH = 1024;
	CartesianPoint getPoint(int index);
	CartesianPoint getNextPoint(CartesianPoint p);
	List<CartesianPoint> getPoints();
	Expression getExpression();
	void setExpression(Expression expr);
	void updateGraph(String interval, Camera camera);
	void updateGraph(Camera camera);
  String getError();
}
