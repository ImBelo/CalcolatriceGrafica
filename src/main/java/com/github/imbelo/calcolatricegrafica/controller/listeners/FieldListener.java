package com.github.imbelo.calcolatricegrafica.controller.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

import com.github.imbelo.calcolatricegrafica.controller.events.ChangeTextBoxEvent;
import com.github.imbelo.calcolatricegrafica.controller.events.ViewEvent;
import com.github.imbelo.calcolatricegrafica.model.graph.*;
import com.github.imbelo.calcolatricegrafica.view.*;

public class FieldListener implements ViewListener,KeyListener{
	private Consumer<ViewEvent> handler;
	private Panel panel;
	public FieldListener(Consumer<ViewEvent> handler, Panel inputPanel) {
		this.handler = handler;
		this.panel = inputPanel;
		panel.getTextBox()
				.stream()
				.map(Pair::getFirst)
				.forEach(field -> field.addKeyListener(this));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		var field = (FunctionField)e.getSource();
		int index = field.getIndex();
		String text = field.getText();
		handler.accept(new ChangeTextBoxEvent(text,index));

	}
	@Override
	public void onViewChanged(ViewEvent event) {
	}
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyPressed(KeyEvent e) {}
}
