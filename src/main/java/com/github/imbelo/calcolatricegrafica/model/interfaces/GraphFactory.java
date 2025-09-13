package com.github.imbelo.calcolatricegrafica.model.interfaces;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Parser;
import java.util.Optional;


public interface GraphFactory {
	public Optional<Graph> createGraph(Expression expr,Parser<Function> parser);
	public Optional<Graph> createGraph(Expression expr);
}
