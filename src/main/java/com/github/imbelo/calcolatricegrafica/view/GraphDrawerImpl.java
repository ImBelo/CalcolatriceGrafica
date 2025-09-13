package com.github.imbelo.calcolatricegrafica.view;

import java.awt.BasicStroke;
import java.util.List;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.graph.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class GraphDrawerImpl implements GraphDrawer{
	private Camera camera;
	List<Graph> graphs;
	private Graphics2D imageDrawer;
	private BufferedImage image;
	private CameraPointConverter converter;
	private CartesianPoint origin;
	private ScreenPoint originScreen;
	public GraphDrawerImpl(Camera camera,List<Graph> graphs, int width, int height) {
		this.camera = CameraImpl.getInstance();
		this.graphs = graphs;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.imageDrawer = image.createGraphics();
		converter = new CameraPointConverter(this.camera);
		origin = new CartesianPointImpl(0.0,0.0);
		originScreen = new CameraPointConverter(this.camera).toScreen(origin);
	}
	@Override
	public void setCamera(Camera camera) {
		this.camera = camera;
		converter = new CameraPointConverter(this.camera);
		origin = new CartesianPointImpl(0.0,0.0);
		originScreen = new CameraPointConverter(this.camera).toScreen(origin);
	}

	@Override
	public void setGraphs(List<Graph> graphs) {
		this.graphs = graphs;
	}

	@Override
	public Image draw() {
		drawBackground();
		drawAxis(); // draws the Axis
		drawNumbers(); // draws the numbers on the cartesian axis
		// draws the function
		drawFunction();
		return image;
	}
	public void drawBackground () { // draws the background
		this.imageDrawer.setColor(Color.BLACK);
		this.imageDrawer.fillRect(0, 0, camera.getWidthPixels(), camera.getHeightPixels());
	}  
	public void drawAxis() { // draws the cartesian axis
		// update of the coordinates of the y and x axis
		CartesianPoint origin = new CartesianPointImpl(0.0,0.0);
		ScreenPoint originScreen = new CameraPointConverter(this.camera).toScreen(origin);
		int xAxisY = originScreen.y();
		int yAxisX = originScreen.x();
		this.imageDrawer.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		imageDrawer.setColor(Color.WHITE);
		imageDrawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// draws the axis
		imageDrawer.drawLine(0, xAxisY, camera.getWidthPixels(), xAxisY);
		imageDrawer.drawLine(yAxisX, 0, yAxisX, camera.getHeightPixels());
		// draws the x and y in their corresponding axis
		imageDrawer.drawString("x", 0, xAxisY - 10);
		imageDrawer.drawString("y", yAxisX + 10, imageDrawer.getFontMetrics().getHeight() - 10);
	}
	public void drawFunction() {
		// draws the functions
		imageDrawer.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		imageDrawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		List<Graph> graphs;
		if(this.graphs == null)
			return;
		
		for(Graph graph : this.graphs) { // iterate all the graphs
			if(graph == null) return;
			List<CartesianPoint> points = graph.getPoints();
			if(points == null) return;
			points
			.forEach(p->{						// iterate all points in each graph
				CartesianPoint next = graph.getNextPoint(p); // in 2 variables functions pNext = p

				// screen coordinate points to draw
				ScreenPoint first = converter.toScreen(p);
				ScreenPoint second = converter.toScreen(next);
				int x1 = first.x();
				int x2 = second.x();
				int y1 = first.y();
				int y2 = second.y();
				// draws line on screen
				imageDrawer.drawLine(x1, y1, x2,y2);
			});
				
		}
			
			
		
	}
	public void drawNumbers() {// draws the numbers on the cartesian axis
		//if(imageDrawer == null) return;
		// update of the screen coordinates of the y and x axis 
		int	xAxisY = originScreen.y();
		int yAxisX = originScreen.x();
		int xNotClose = findNotClose(); // smallest x cartesian cordinate that is not too close
		imageDrawer.setFont(new Font("courier new", Font.ROMAN_BASELINE, 25));
		CartesianPoint center = camera.getCenter();
		double centerX = center.x();
		double centerY = center.y();
		double halfWidth = camera.getCameraWidth()/2;
		double halfHeight = camera.getCameraHeight()/2;
		int left = (int)Math.floor(centerX - halfWidth);
	 	int right = (int)Math.ceil(centerX + halfWidth);
	 	int down = (int)Math.floor(centerY - halfHeight);
	 	int up = (int)Math.ceil(centerY + halfHeight);
		// y number inside camera
	 	int i = down;
	 	while(i<up) {
			if(i % xNotClose == 0) {
				int y = converter.toScreen(new CartesianPointImpl(0.0,(double)i)).y();
				// draws numbers in y axis
				imageDrawer.drawString(Integer.toString(i), yAxisX, y);
				// draws the axis parallel to the x axis
				imageDrawer.setStroke(new BasicStroke(0.3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
				imageDrawer.drawLine(0,y,camera.getWidthPixels() ,y);// x axis
				i+=xNotClose;
			}
			else {
				i++;
			}	 
		}
	 	// x number inside camera
	 	i = left;
		while(i<right) {
			if(i % xNotClose == 0) {
				int x = converter.toScreen(new CartesianPointImpl((double) i,0.0)).x();
				// draws number in x axis
				imageDrawer.drawString(Integer.toString(i), x, xAxisY);
				// draws the axis parallel to the y axis
				imageDrawer.setStroke(new BasicStroke(0.3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
				imageDrawer.drawLine(x, 0, x, camera.getHeightPixels());
				
				i+=xNotClose;
			}
			else {
				i++;
			}
			 
	}
		
	}
	private int findNotClose() {	// finds the point (x,0) which is considered not close
		int pixelsTooClose = 100; // number of pixel that is considered too close
		int xNotClose = 1; // cartesian coordinate of a point
		ScreenPoint point = converter.toScreen(new CartesianPointImpl((double)xNotClose,0.0));
	ScreenPoint origin  = converter.toScreen(new CartesianPointImpl(0.0,0.0));

		while(point.x() - origin.x() < pixelsTooClose) {
			xNotClose++;
			point = converter.toScreen(new CartesianPointImpl((double)xNotClose,0.0));

		}
		return xNotClose;
	}
}
