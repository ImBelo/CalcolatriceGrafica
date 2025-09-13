package com.github.imbelo.calcolatricegrafica.view;

import java.awt.event.KeyListener;



public interface Field {
	String getText();
	void addKeyListener(KeyListener keyListener);
	void error();
	void noError();
}
