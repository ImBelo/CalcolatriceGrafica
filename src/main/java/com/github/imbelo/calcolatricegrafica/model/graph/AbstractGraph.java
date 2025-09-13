package com.github.imbelo.calcolatricegrafica.model.graph;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import java.util.LinkedList;
import java.util.List;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
public abstract class AbstractGraph implements Graph{
	private List<CartesianPoint> points;
	private Interval[] intervals;
	private Expression expr;
	private Function function; // abstract syntax tree
	private String lastIntervalString = "";
	private Parser<Function> parser;
	private Camera camera;
  private String error;
	public AbstractGraph(Expression expr,Parser<Function> parser)  {
		this.expr = expr;
		this.parser = parser;
    var result = parser.parse(expr);
    if(result.isPresent()){
		  this.function = parser.parse(expr).get();
    }else{
      this.error = parser.getError();
    }
		points = new LinkedList<CartesianPoint>();
		intervals = new Interval[2];
	}
	public void setParser(Parser<Function> parser) {
		this.parser=parser;
		this.function=parser.parse(expr).get();
		
	}
	public void updateGraph(Camera camera) {
		this.camera = camera;
		updateGraph(lastIntervalString, camera);
	}
	@Override
	public void updateGraph(String interval, Camera camera) {
		this.camera = camera;
		lastIntervalString = interval;
		Interval[] intervals1 = IntervalFactory.createInterval(interval);
		Interval[] defaultIntervals = createDefaultInterval();
		this.intervals = defaultIntervals;
		for(int i = 0;i<intervals1.length;i++){
			if(intervals1!= null && intervals1[i] != null)
				this.intervals[i] = intervals[i].intersectWith(intervals1[i]);
		}
		this.points = iterateAndCheckIfPointInGraph();
	}
	public Interval[] createDefaultInterval() {
		Camera camera = CameraImpl.getInstance();
		double cameraHeight = camera.getCameraHeight();
		double cameraWidth = camera.getCameraWidth();
		CartesianPoint center = camera.getCenter();
		double cameraX = center.x();
		double cameraY = center.y();
		// bounds of interval
		double right = cameraWidth/2;
		double left = -right;
		double up = cameraHeight/2;
		double down = -up;
		// creations of interval 
		Interval intervalX = new IntervalImpl(left,right);
		Interval intervalY = new IntervalImpl(down,up);
		// traslation of the interval
		intervalX.shift(cameraX); 
		intervalY.shift(cameraY); 	
		Interval[] myIntervals = new Interval[2];
		myIntervals[0] = intervalX;
		myIntervals[1] = intervalY;
		return myIntervals;
		
	}
	// method to calculate points
	public abstract List<CartesianPoint> iterateAndCheckIfPointInGraph();
	public abstract CartesianPoint getPoint(int index);
	public abstract CartesianPoint getNextPoint(CartesianPoint p);
	public void setInterval(Interval ...intervals) {
		this.intervals = intervals;
	}
	public Interval[] getInterval() {
		return this.intervals;
	}
	public void setExpression(Expression expr) {
		this.expr = expr;
		this.function = parser.parse(expr).get();
	}
	public Expression getExpression() {
		return this.expr;
	}
  @Override
  public String getError(){
    return this.error;
  }
	@Override
	public List<CartesianPoint> getPoints(){
		return this.points;
	}
	public void setPoints(List<CartesianPoint> points){
		this.points = points;
	}
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}	
}
