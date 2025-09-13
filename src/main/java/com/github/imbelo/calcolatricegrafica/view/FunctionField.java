package com.github.imbelo.calcolatricegrafica.view;

import java.awt.*;

import javax.swing.JTextField;


public class FunctionField extends JTextField implements Field{
	private static final long serialVersionUID = 1L;
	protected static final int WIDTH = 226;
	private static final int HEIGHT = 50;
	private static int xPos = 0;
	private static int yPos = 0;
	private int index = 0;
	private Field linkedIntervalField;
	public FunctionField() {
		super();
		index = yPos/HEIGHT;
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		this.setFont(font1);
		this.setBounds(xPos,yPos,WIDTH,HEIGHT);
	}
	public static void next(){
		yPos+=HEIGHT;
	}
	public static void prev(){
		yPos-=HEIGHT;
	}
	@Override
	public String getText() {
		return super.getText();
	}
	@Override
	public void error() {
		this.setBackground(Color.RED);
	}
	@Override
	public void noError() {
		this.setBackground(Color.WHITE);
	}
	public void setFunction(String function) {
		super.setText(function);
	}
	public int getIndex() {
		return index;
	}
}
