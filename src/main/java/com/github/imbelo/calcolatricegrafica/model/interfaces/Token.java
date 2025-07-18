package com.github.imbelo.calcolatricegrafica.model.interfaces;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Valuable;

public interface Token extends Node<Token>, Valuable{
	Type getType();
	String getData();
}
