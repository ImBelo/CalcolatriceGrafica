package com.github.imbelo.calcolatricegrafica.view;

import java.util.List;

import com.github.imbelo.calcolatricegrafica.model.graph.*;


public interface View {
	MyWindow getWindow();
	Button getAddButton();
	Button getRemoveButton();
	Panel getInputPanel();
	List<Pair<Field, Field>> getFunctionField();
	ErrorField getErrorField();
}
