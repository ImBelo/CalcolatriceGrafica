package com.github.imbelo.calcolatricegrafica.model.interfaces;

import java.util.List;


public interface Graph {
	static final int HEIGHT = 600;
	static final int WIDTH = 1024;
	public CartesianPoint getPoint(int index);
	public CartesianPoint getNextPoint(CartesianPoint p);
	public List<CartesianPoint> getPoints();
	public Expression getExpression();
	public void setExpression(Expression expr);
	public void updateGraph(String interval, Camera camera);
	public void updateGraph(Camera camera);
}
