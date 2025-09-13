package com.github.imbelo.calcolatricegrafica.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.imbelo.calcolatricegrafica.controller.events.ViewEvent;
import com.github.imbelo.calcolatricegrafica.view.Button;

public class RemoveButtonListener implements ViewListener,ActionListener{
	Runnable action;
	public RemoveButtonListener(Runnable action, Button button){
		this.action = action;
		button.addActionListener(this);
	}

	@Override
	public void onViewChanged(ViewEvent event) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		action.run();
	}
}
