package com.github.imbelo.calcolatricegrafica.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.github.imbelo.calcolatricegrafica.controller.*;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Expression;
import com.github.imbelo.calcolatricegrafica.model.graph.*;
import com.github.imbelo.calcolatricegrafica.model.parser.*;

public class InputPanel extends JPanel implements Panel{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 276*3/2;
	private static final int HEIGHT = 600;
	public static final int TEXTHEIGHT = 50;
	private Button plus;
	private Button minus;
	private List<Pair<Field,Field>> textBox;
	private BorderLayout border;
	private Controller controller;
	
	public InputPanel(BorderLayout border){
		this.border = border;
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		textBox = new ArrayList<Pair<Field,Field>>();
		this.setBounds(0,0,WIDTH,HEIGHT);
		// first input field
		FunctionField firstFun = new FunctionField();
		IntervalField firstInt = new IntervalField();
		addText(new Pair<Field,Field>(firstFun,firstInt));
		createAddButton();
		createDeleteButton();
	}
	
	public List<String> getText() {
	  	return textBox
				.stream()
				.map(Pair::getFirst)
				.map(Field::getText)
				.toList();
	}

	public void updateButtons() {
		if(textBox.size()<12) {
			plus.setBounds(WIDTH-TEXTHEIGHT+5, TEXTHEIGHT*(textBox.size()-1), TEXTHEIGHT, TEXTHEIGHT);
			minus.setBounds((WIDTH-TEXTHEIGHT+5),TEXTHEIGHT*(textBox.size()-2), TEXTHEIGHT, TEXTHEIGHT);
		}
		this.updateUI();
	}
	public void addText(Pair<Field,Field> pair) {
		if(textBox.size()<11) {
		FunctionField.next();
		IntervalField.next();
		textBox.add(pair);
		Field f = pair.getFirst();
		Field f2 = pair.getSecond();	
		this.add((Component) f,BorderLayout.WEST);
		this.add((Component) f2,BorderLayout.WEST);
		}

	}

	public void createTextBox(){
		Field newField = new FunctionField();
		Field newInterval = new IntervalField();
		addText(new Pair<Field,Field>(newField,newInterval));
		// places buttons
		updateButtons();
	}
	public void removeText() {
		int last = textBox.size()-1;
		FunctionField.prev();
		IntervalField.prev();
		this.remove((Component) textBox.get(last).getFirst());
		this.remove((Component) textBox.get(last).getSecond());
		textBox.remove(last);
		updateButtons();
	}

	public List<Expression> getFunctions() {
		return textBox
				.stream()
				.map(a->ExpressionImpl.of(a.getFirst().getText()))
				.toList();
	}
	
	
		
	
	private void createAddButton() {
		plus = new MyButton();
		plus.setText("+");
		plus.setBounds(WIDTH-TEXTHEIGHT, 0 , TEXTHEIGHT, TEXTHEIGHT);
		plus.setSize(TEXTHEIGHT, TEXTHEIGHT);
		this.add((JButton) plus);
		
	}
	private void createDeleteButton() {
		minus = new MyButton();
		minus.setText("-");
		minus.setBounds(WIDTH-TEXTHEIGHT,-TEXTHEIGHT , TEXTHEIGHT, TEXTHEIGHT);
		minus.setSize(TEXTHEIGHT, TEXTHEIGHT);
		this.add((JButton) minus);
	}
	public List<Pair<Field,Field>> getTextBox() {
		return this.textBox;
	}

	public Button getAddButton() {
		return this.plus;	
	}
	public Button getRemoveButton() {
		return this.minus;	
	}

	
	
	
	
	
}
