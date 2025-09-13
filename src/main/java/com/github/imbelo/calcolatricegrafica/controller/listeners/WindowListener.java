package com.github.imbelo.calcolatricegrafica.controller.listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.function.Consumer;

import com.github.imbelo.calcolatricegrafica.controller.events.PanEvent;
import com.github.imbelo.calcolatricegrafica.controller.events.ViewEvent;
import com.github.imbelo.calcolatricegrafica.controller.events.ZoomEvent;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Camera;
import com.github.imbelo.calcolatricegrafica.model.interfaces.CartesianPoint;
import com.github.imbelo.calcolatricegrafica.model.graph.*;
import com.github.imbelo.calcolatricegrafica.view.MyWindow;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;

public class WindowListener implements ViewListener,MouseWheelListener,MouseListener,MouseMotionListener{
	private Point mousePt;
	private MyWindow myWindow;
	private final Consumer<ViewEvent> viewHandler;
	private Camera camera;
	public WindowListener(Consumer<ViewEvent> handler,MyWindow window,Camera camerainit) {
		this.viewHandler = handler;
		myWindow = window;
		myWindow.addMouseWheelListener(this);
		myWindow.addMouseMotionListener(this);
		myWindow.addMouseListener(this);
		this.camera = camerainit;

	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double scale = Math.pow(1.15, e.getPreciseWheelRotation());// number between )0,+inf(
		PointConverter pConverter = new CameraPointConverter(this.camera);
		ScreenPoint screenPoint = new ScreenPointImpl(e.getX(),e.getY());
		CartesianPoint center = pConverter.toCartesian(screenPoint);
		viewHandler.accept(new ZoomEvent(
				scale,
				center
		));
		repaintWindow();
	}
	private void repaintWindow() {
		myWindow.repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		this.mousePt = e.getPoint();
        myWindow.requestFocusInWindow();
		repaintWindow();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		double aspectRatioY = camera.getCameraHeight()/camera.getHeightPixels();
		double aspectRatioX = camera.getCameraWidth()/ camera.getWidthPixels();
		double dx = (e.getX() - this.mousePt.x)*aspectRatioX;
        double dy = (e.getY() - this.mousePt.y)*aspectRatioY;
		CartesianPoint vector = new CartesianPointImpl(dx,dy);

		viewHandler.accept(new PanEvent(vector));
		this.mousePt = e.getPoint();
		repaintWindow();
	}

	@Override public void mouseMoved(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}

	@Override
	public void onViewChanged(ViewEvent event) {
		if(event instanceof ZoomEvent zEvent)
			zEvent.getCamera().ifPresent(camera -> this.camera = camera);

	}

}
