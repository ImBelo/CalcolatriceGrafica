package com.github.imbelo.calcolatricegrafica.model.graph;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Camera;
import com.github.imbelo.calcolatricegrafica.model.interfaces.CartesianPoint;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Graph;
import com.github.imbelo.calcolatricegrafica.model.interfaces.ScreenPoint;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;


// contains the data of a subset of the cartesian plane
public class CameraImpl implements Camera{
	// singleton
	private static Camera INSTANCE;
	CartesianPoint center;
	private double cameraWidth; // width of camera in carteesian coordinate
	private double cameraHeight; // height of camera in carteesian coordinate
	private int WIDTH = 1024; //pixels
	private int HEIGHT = 600; //pixels
	private double scale;
	private double MAX_ZOOM = 1E7;
	private double MIN_ZOOM= 1E-2;
	
	private CameraImpl() {
		center = new CartesianPointImpl(0.0,0.0);
		this.cameraWidth=2.0*WIDTH/HEIGHT;
		this.cameraHeight=2.0;
	}
	public static Camera getInstance() {
		if(INSTANCE == null)
			INSTANCE = new CameraImpl();
		return INSTANCE;
	}
	public CartesianPoint getCenter(){
		return center;
	}


	public void setCenter(CartesianPoint newCenter){
		this.center = newCenter;
	}
	public double getCameraWidth() {
		return cameraWidth;
	}
	public void setCameraWidth(double cameraWidth) {
		this.cameraWidth = cameraWidth;
	}
	public double getCameraHeight() {
		return cameraHeight;
	}
	public void setCameraHeight(double cameraHeight) {
		this.cameraHeight = cameraHeight; 
	}
	private double bottom() { // the smallest coordinate y visible in the graph 
		return center.y() - halfGraphHeight();
	}
	
	private double right() { // the biggest coordinate x visible in the graph
		return center.x() - halfGraphWidth();
	}
	
	public double toCartesianX(int screenX) { // trasforms x screen coordinate to x cartesian coordinate
		return screenX / (double)WIDTH * cameraWidth + right();
	}
	
	public double toCartesianY(int screenY) { // trasforms y screen coordinate to y cartesian coordinate
		return (HEIGHT - screenY) / (double)HEIGHT * cameraHeight + bottom();
	}
    public int toScreenX(double realX) { // trasforms x cartesian coordinate to y screen coordinate
		return (int) ((realX - right()) / cameraWidth * WIDTH);
	}
	
	public int toScreenY(double realY) { // trasforms y cartesian coordinate to y screen coordinate
		return HEIGHT - (int) ((realY - bottom()) / cameraHeight * HEIGHT);
	}

	public double halfGraphWidth() {
		return cameraWidth / 2.0;
	}
	
	public double halfGraphHeight() { 
		return cameraHeight / 2.0;
	}
	public void setHeightPixels(int height) {
		this.HEIGHT = height;
	}
	public void SetWidthPixels(int width) {
		this.WIDTH = width;
	}
	public int getHeightPixels() {
		return HEIGHT;
	}
	public int getWidthPixels() {
		return WIDTH;
	}

	@Override
	public double getScale() {
		return this.scale;
	}

	public void zoom(double scale, CartesianPoint focus) {
		double scaledWidth = this.cameraWidth*scale;
		if(scaledWidth < MAX_ZOOM &&
		   scaledWidth > MIN_ZOOM) {
			// zooms the camera in if scale < 1 and out if scale > 1
			this.cameraWidth *= scale;
			this.cameraHeight *= scale;
			// adjust the center of the cartesian plane 
			// (zoom out center gets closer, zoom in center gets further)
			this.center = new CartesianPointImpl(focus.x()+(this.center.x()-focus.x())*scale,
			focus.y()+(this.center.y()-focus.y())*scale);
		}
		
	}
	public void pan(CartesianPoint vector) {
		this.center = new CartesianPointImpl( center.x()-vector.x(),
											  center.y()+vector.y());

	}
		
	
}
