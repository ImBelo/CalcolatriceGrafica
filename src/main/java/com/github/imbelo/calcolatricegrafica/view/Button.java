package com.github.imbelo.calcolatricegrafica.view;

import java.awt.event.ActionListener;



public interface Button{

	void setBounds(int i, int j, int textheight, int textheight2);
	void setText(String string);
	void setSize(int textheight, int textheight2);
	void addActionListener(ActionListener actionListener);

}
