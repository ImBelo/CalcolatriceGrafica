package com.github.imbelo.calcolatricegrafica.controller;

import com.github.imbelo.calcolatricegrafica.controller.listeners.ViewListener;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Model;
import com.github.imbelo.calcolatricegrafica.view.View;


public interface Controller {
	Model getModel();
	View getView();
	void addViewListener(ViewListener observer);
	void removeViewListener(ViewListener listener);
	void start();

	
}
