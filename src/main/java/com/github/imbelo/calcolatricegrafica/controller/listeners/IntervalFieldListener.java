package com.github.imbelo.calcolatricegrafica.controller.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

import com.github.imbelo.calcolatricegrafica.controller.events.ChangeTextBoxEvent;
import com.github.imbelo.calcolatricegrafica.controller.events.ViewEvent;
import com.github.imbelo.calcolatricegrafica.model.graph.*;
import com.github.imbelo.calcolatricegrafica.view.*;

public class IntervalFieldListener implements ViewListener,KeyListener{
	private Consumer<ViewEvent> handler;
	private Panel panel;
	public IntervalFieldListener(Consumer<ViewEvent> handler,Panel panel) {
		this.handler = handler;
		this.panel = panel;
		panel.getTextBox()
				.stream()
				.map(Pair::getSecond).
				forEach(field -> field.addKeyListener(this));
	}
	@Override
	public void keyReleased(KeyEvent e) {
		var field = (IntervalField)e.getSource();
		int index = field.getIndex();
		String text = field.getText();
		handler.accept(new ChangeTextBoxEvent(text,index));
	}
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyPressed(KeyEvent e) {}

	@Override
	public void onViewChanged(ViewEvent event) {

	}
}
