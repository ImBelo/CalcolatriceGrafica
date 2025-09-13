package com.github.imbelo.calcolatricegrafica.view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Camera;

public class MyWindow extends JPanel{
	private final int WIDTH = 1024;
	private final int HEIGHT = 600;
	private Image image;     
	private GraphDrawer graphDrawer;

	public MyWindow() { // constructor
		setFocusable(true);
		requestFocusInWindow();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	protected void paintComponent(Graphics g) { // this method is called every event that changes the window	
		super.paintComponent(g);
		if(graphDrawer == null) return;
		image = graphDrawer.draw();
		g.drawImage(image, 0, 0, null);

	}

	public void setGraphDrawer(GraphDrawer graphDrawer) {
		this.graphDrawer = graphDrawer;
	}
	public void update(Camera camera){
		this.graphDrawer.setCamera(camera);
	}

	public int getHeight() {
		return HEIGHT;
	}
	public int getWidth() {
		return WIDTH;
	}

	



	


	
	
}