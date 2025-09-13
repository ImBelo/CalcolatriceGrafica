package com.github.imbelo.calcolatricegrafica.controller;


import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.github.imbelo.calcolatricegrafica.controller.events.ChangeTextBoxEvent;
import com.github.imbelo.calcolatricegrafica.controller.events.PanEvent;
import com.github.imbelo.calcolatricegrafica.controller.events.ViewEvent;
import com.github.imbelo.calcolatricegrafica.controller.events.ZoomEvent;
import com.github.imbelo.calcolatricegrafica.controller.listeners.*;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Camera;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Expression;
import com.github.imbelo.calcolatricegrafica.model.parser.*;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Model;
import com.github.imbelo.calcolatricegrafica.view.MyWindow;
import com.github.imbelo.calcolatricegrafica.view.View;


public class ControllerImpl implements Controller {
	private Model model;
	private View view;
	public List<ViewListener> listeners = new LinkedList<>();

	public ControllerImpl(Model model, View view) {
		this.model = Objects.requireNonNull(model);
		this.view = Objects.requireNonNull(view);
	}

	public void addViewListener(ViewListener viewListener) {
		listeners.add(viewListener);
	}
	public void removeViewListener(ViewListener viewListener){
		listeners.remove(viewListener);
	}
	@Override
	public void start() {
		MyWindow window = view.getWindow();
		Camera camera = model.getCamera();
		window.setGraphDrawer(model.getGraphDrawer());
		ViewListener windowListener = new WindowListener(this::handleWindowEvent,view.getWindow(),model.getCamera());
		ViewListener fieldListener = new FieldListener(this::handleFunctionBoxChangedEvent,view.getInputPanel());
		ViewListener intervalFieldListener = new IntervalFieldListener(this::handleIntervalBoxChangedEvent,view.getInputPanel());
		ViewListener removeButtonListener = new RemoveButtonListener(this::handleRemoveButtonEvent,view.getRemoveButton());
		ViewListener addButtonListener = new AddButtonListener(()->handleAddButtonEvent((KeyListener) fieldListener, (KeyListener) intervalFieldListener), view.getAddButton());
		ViewListener graphDrawerListener = new GraphDrawerListener(this::handleGraphDrawerEvent);
		// add all eventListener
		addViewListener(windowListener);
		addViewListener(fieldListener);
		addViewListener(intervalFieldListener);
		addViewListener(removeButtonListener);
		addViewListener(addButtonListener);
		addViewListener(graphDrawerListener);
	}

	private void handleIntervalBoxChangedEvent(ViewEvent event){
		if(event instanceof ChangeTextBoxEvent iEvent){
			int i = iEvent.getIndex();
			String text = iEvent.getText();
			model.setInterval(i,(text));
			model.updateGraph(i,text);
			handleGraphDrawerEvent();
		}
	}

	private void cameraChanged() {
		ViewEvent event = new ZoomEvent(
				model.getCamera()
		);
		listeners.forEach(l -> l.onViewChanged(event));
	}
	private void handleFunctionBoxChangedEvent(ViewEvent event){
		if(event instanceof ChangeTextBoxEvent tEvent){
			int i = tEvent.getIndex();
			String text = tEvent.getText();
			Expression expr = ExpressionImpl.of(text);
			model.setExpression(i,expr);
			boolean notWellFormed = model.createGraph(i,expr);
			handleGraphCreation(i,notWellFormed);
			model.updateGraphs();
			handleGraphDrawerEvent();
		}
	}
	private void handleGraphCreation(int i,boolean notWellFormed){
		var field = view.getInputPanel().getTextBox().get(i).getFirst();
		var errorField = view.getErrorField();
		String error = model.getError().orElse("");
		if(notWellFormed) {
			field.error();
			errorField.setText(error);
		} else {
			field.noError();
			errorField.setText("");
		}
	}
	private void handleWindowEvent(ViewEvent event) {
		Camera camera = model.getCamera();
		if(event instanceof ZoomEvent zEvent && zEvent.getScale().isPresent() && zEvent.getFocus().isPresent()){
			camera.zoom(zEvent.getScale().get(),zEvent.getFocus().get());
		}
		if(event instanceof PanEvent pEvent && pEvent.getVector().isPresent()){
			camera.pan(pEvent.getVector().get());
		}
		try {
			model.updateGraphs();
		}catch(Exception ignored){

		}
		handleGraphDrawerEvent();
        //updates view
		cameraChanged();
        //updates model
	}
	private void handleRemoveButtonEvent(){
		view.getInputPanel().removeText();
		model.removeLast();
		handleGraphDrawerEvent();
	}
	private void handleAddButtonEvent(KeyListener function,KeyListener interval){
		view.getInputPanel().createTextBox();
		updateTextBox(function,interval);
		model.addExpressions();
	}

	private void updateTextBox(KeyListener function,KeyListener interval) {
		view.getInputPanel().getTextBox()
				.forEach(pair->{
					pair.getFirst().addKeyListener(function);
					pair.getSecond().addKeyListener(interval);
				});

	}
	public void handleGraphDrawerEvent(){
		Camera camera = model.getCamera();
		MyWindow window = view.getWindow();
		window.update(camera);
		window.repaint();
	}

	public List<ViewListener> getListeners() {
		return listeners;
	}
	@Override
	public Model getModel() {
		return this.model;
	}
	@Override
	public View getView() {
		return this.view;
	}

}
