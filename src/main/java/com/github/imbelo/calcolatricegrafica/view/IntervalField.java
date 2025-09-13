package com.github.imbelo.calcolatricegrafica.view;

import java.awt.*;

import javax.swing.JTextField;

public class IntervalField extends JTextField implements Field{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 145;
	private static final int HEIGHT = 50;
	private static int xPos = FunctionField.WIDTH;
	private static int yPos = 0;
	private int index = 0;
	private Field linkedFunctionField;
	public IntervalField(){
		super();
		index = yPos/HEIGHT;
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		this.setFont(font1); 
		this.setBounds(xPos,yPos,WIDTH,HEIGHT);
		
	}
	public FunctionField getLinkedField() {
		return (FunctionField)linkedFunctionField;
	}
	public static void next(){
		yPos+=HEIGHT;
		
	}
	public static void prev(){
		yPos-=HEIGHT;
	}
	public int getIndex() {
		return index;
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
}
