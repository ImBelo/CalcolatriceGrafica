package com.github.imbelo.calcolatricegrafica.view;

import java.util.List;

import com.github.imbelo.calcolatricegrafica.model.graph.*;;

public interface Panel {
	void updateButtons();
	List<Pair<Field, Field>> getTextBox();
	void createTextBox();
	void removeText();
	int getWidth();
	int getHeight();
	Button getAddButton();
	Button getRemoveButton();
	void addText(Pair<Field, Field> pair);


}
