package com.github.imbelo.calcolatricegrafica.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import com.github.imbelo.calcolatricegrafica.controller.events.ViewEvent;
import com.github.imbelo.calcolatricegrafica.view.*;

public class AddButtonListener implements ViewListener, ActionListener {
	private final Runnable action;
	public AddButtonListener(Runnable action, Button button) {
		this.action = action;
		button.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		action.run();
	}

	@Override
	public void onViewChanged(ViewEvent event) {

	}


}
