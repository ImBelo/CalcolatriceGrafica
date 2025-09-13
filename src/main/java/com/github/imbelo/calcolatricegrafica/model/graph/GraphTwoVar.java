package com.github.imbelo.calcolatricegrafica.model.graph;
import java.util.LinkedList;
import java.util.List;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;

public class GraphTwoVar extends AbstractGraph{
	public GraphTwoVar(Expression expr,Parser parser) {
		super(expr,parser);	
	}
	
	@Override
	public CartesianPoint getPoint(int index) {
		return getPoints() != null?getPoints().get(index):null;
	}

	@Override
	public CartesianPoint getNextPoint(CartesianPoint p) {
		return p;
	}
	
	@Override
	public List<CartesianPoint> iterateAndCheckIfPointInGraph() {
		List<CartesianPoint> points = new LinkedList<>();
		// discretization of the Interval
		Camera camera = CameraImpl.getInstance();
		double cameraWidth = camera.getCameraWidth(); 
		double cameraHeight = camera.getCameraHeight();
		double dx = cameraWidth/WIDTH;
		double dy = cameraHeight/HEIGHT;  
		double z=Double.NaN;
		// partial derivative of a point (x,y)
		double fx = 0;  // with respect of x
		double fy = 0;  // with respect of y 
		double variablesError = 1E-4;	
		double functionError = 0; // error that a function on a point x,y could have
		double zoomFactor = cameraHeight/8;  
		Interval[] intervals = getInterval();
		Function f = getFunction();
		// its slow possible improvement
		// TODO:
		// https://www.researchgate.net/publication/220393631_An_adaptive_algorithm_for_efficient_computation_of_level_curves_of_surfaces
		if(f == null || intervals == null)
			return null;
		for (double y = intervals[1].getLeft(); y < intervals[1].getRight(); y+=dy) { // cartesian plane y coordinate
			for (double x = intervals[0].getLeft(); x < intervals[0].getRight(); x+=dx) { // cartesian plane x coordinate				
				z = getFunction().evaluateAt(x,y); 
				// limit definition	
				fx = (getFunction().evaluateAt(x+1E-3,y)-z)/1E-3; 
				fy = (getFunction().evaluateAt(x,y+1E-3)-z)/1E-3;
				// https://en.wikipedia.org/wiki/Propagation_of_uncertainty 
				// Simplification paragraph
				functionError = Math.sqrt((fx*fx+fy*fy)*variablesError);
						
				//this instead of z == 0.0 cause of loss of precision
				if(Math.abs(z)<functionError*zoomFactor)
					points.add(new CartesianPointImpl(x,y));
			}
		}
		return points;
		
}
	
	
	
	

}
