package com.github.imbelo.calcolatricegrafica.model.interfaces;

import com.github.imbelo.calcolatricegrafica.view.GraphDrawer;

import java.util.List;
import java.util.Optional;

public interface Model {
	
	Camera getCamera();
	List<Graph> getGraphs();
	boolean createGraph(int index, Expression expression);
	void updateGraph(int index,String interval);
	void updateGraphs();
	GraphDrawer getGraphDrawer();
	void setExpression(int i,Expression expression);
	void setInterval(int i, String string);
	void addExpressions();
	Optional<String> getError();
	void removeLast();
}
