package com.github.imbelo.calcolatricegrafica.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class ErrorField extends JTextField {
	private static final long serialVersionUID = 1L;

	public ErrorField() {
		super();
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		this.setFont(font1);
		this.setSelectedTextColor(Color.red);
		this.setBounds(0, 565, 415, 50);

	}

}