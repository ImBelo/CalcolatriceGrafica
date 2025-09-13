package com.github.imbelo.calcolatricegrafica.model.graph;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Interval;

public class IntervalImpl implements Interval{
	private double left;
	private double right;
	public IntervalImpl(double left, double right) { 
		this.right = Math.max(left, right);
		this.left = Math.min(left, right);
	}
	public double getLeft() {
		return left;
	}
	public void setLeft(double sx) {
		this.left = sx;
	}
	public double getRight() {
		return right;
	}
	public void setRight(double dx) {
		this.right = dx;
	}
	public void shift(double n) {
		this.left+=n;
		this.right+=n;
	}
	public Interval intersectWith(Interval interval) {
		if(interval == null) return this;
		return new IntervalImpl(Math.max(this.left, interval.getLeft()),
								Math.min(this.right, interval.getRight()));
	}
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
            
		if (obj == null || !(obj instanceof Interval)) 
            return false;
		Interval otherInterval = (Interval) obj;
        
		if(otherInterval.getLeft() != this.getLeft() || otherInterval.getRight() != this.getRight()) 
			return false;
		
		return true;		
	}
		
		
}

