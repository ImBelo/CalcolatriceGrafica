package com.github.imbelo.calcolatricegrafica.model.factories;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.github.imbelo.calcolatricegrafica.model.parser.*;
import com.github.imbelo.calcolatricegrafica.model.token.*;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.graph.*;

public class GraphFactoryImpl implements GraphFactory{
	private Map<Arity, Function<Expression,Graph>> graphSuppliers;
	// default parser
	private Parser<com.github.imbelo.calcolatricegrafica.model.interfaces.Function> parser;
	public GraphFactoryImpl(Parser<com.github.imbelo.calcolatricegrafica.model.interfaces.Function> parser) {
		this.parser = parser;
		graphSuppliers = new HashMap<>();
		graphSuppliers.put(Arity.Unary,  (expr)->new GraphSingleVar(expr,this.parser));
		graphSuppliers.put(Arity.Binary, (expr)->new GraphTwoVar(expr,this.parser));
	}
	public void putGraph(Arity arity, Function<Expression,Graph> supplier) {
		graphSuppliers.put(arity,supplier);
	}
	//using default parser
	public Optional<Graph> createGraph(Expression expr) {
		Arity arity = expr.getArity();
        return Optional.ofNullable(graphSuppliers.get(arity).apply(expr));
    }
	//using given parser
	public Optional<Graph> createGraph(Expression expr,Parser<com.github.imbelo.calcolatricegrafica.model.interfaces.Function> parser) {
		Arity arity = expr.getArity();
        return Optional.ofNullable(graphSuppliers.get(arity).apply(expr));
	}
}
