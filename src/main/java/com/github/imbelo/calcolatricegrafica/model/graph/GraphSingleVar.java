package com.github.imbelo.calcolatricegrafica.model.graph;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;

public class GraphSingleVar extends AbstractGraph{
	public GraphSingleVar(Expression expr,Parser parser) {
		super(expr,parser);	
	}
	@Override
	public CartesianPoint getPoint(int index) {
		return getPoints() != null?getPoints().get(index):null;
	}
	@Override
	public CartesianPoint getNextPoint(CartesianPoint p) {
		List<CartesianPoint> points = getPoints();
		boolean notLast = points.indexOf(p)+1<points.size();
		CartesianPoint pNext=null;
		if (notLast)
			pNext = getPoint(points.indexOf(p)+1);
		return notLast?pNext:p;
	}

	@Override
	public List<CartesianPoint> iterateAndCheckIfPointInGraph() {
		List<CartesianPoint> points = new LinkedList<>();
		Interval interval = super.getInterval()[0];
		Function function = getFunction();
		// discretization of the interval
		double dx = CameraImpl.getInstance().getCameraWidth()/WIDTH;
		// loops all x cartesian coordinate
		if(getFunction() == null || interval == null)
			return null;
		DoubleStream
				.iterate(interval.getLeft(),x -> x < interval.getRight(),x -> x + dx)
				.forEach(x -> points.add(new CartesianPointImpl(x,function.evaluateAt(x))));
		return points;
		
	}
	
	

	

}
