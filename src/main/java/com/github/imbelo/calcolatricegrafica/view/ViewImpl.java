package com.github.imbelo.calcolatricegrafica.view;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;

import com.github.imbelo.calcolatricegrafica.model.graph.*;

public class ViewImpl implements View{
	private Panel textsInput;
	private MyWindow window;
	private ErrorField errorfield;
	private final int FRAMEX = 1350;
	private final int FRAMEY = 650;
	private int INPUTPANELX;
	private int INPUTPANELY;
	public ViewImpl() {
		this.window = new MyWindow();

		BorderLayout border = new BorderLayout();
		this.textsInput = new InputPanel(border);
		errorfield = new ErrorField();
		SwingUtilities.invokeLater(() -> {
			JFrame frame  = new JFrame("FunctionGrapher");
			INPUTPANELX = textsInput.getWidth();
			INPUTPANELY = textsInput.getHeight();
			frame.setResizable(false);
			frame.setSize(FRAMEX,FRAMEY);
			window.setBounds(INPUTPANELX,0,FRAMEX-INPUTPANELX,FRAMEY);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setLayout(null);
			frame.add((InputPanel) textsInput,BorderLayout.WEST);
			frame.add(errorfield,BorderLayout.WEST);
			frame.add(window,BorderLayout.EAST);
		});

		
	}
	@Override
	public MyWindow getWindow() {
		return this.window; 
	}
    @Override
	public Panel getInputPanel() {
		return this.textsInput;
	}
	@Override
	public List<Pair<Field,Field>> getFunctionField() {
		return this.textsInput.getTextBox();
	}
	@Override
	public Button getAddButton() {
		return this.textsInput.getAddButton();
	}
	public Button getRemoveButton() {
		return this.textsInput.getRemoveButton();
	}
	@Override
	public ErrorField getErrorField() {
		return this.errorfield;
	}

}

