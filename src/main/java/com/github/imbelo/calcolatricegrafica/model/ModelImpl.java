package com.github.imbelo.calcolatricegrafica.model;

import java.util.*;

import com.github.imbelo.calcolatricegrafica.model.factories.*;
import com.github.imbelo.calcolatricegrafica.model.interfaces.*;
import com.github.imbelo.calcolatricegrafica.model.parser.*;
import com.github.imbelo.calcolatricegrafica.model.graph.*;
import com.github.imbelo.calcolatricegrafica.model.token.*;
import com.github.imbelo.calcolatricegrafica.view.GraphDrawer;
import com.github.imbelo.calcolatricegrafica.view.GraphDrawerImpl;

public class ModelImpl implements Model {
	private List<Graph> graphs;
	private Camera camera;
	private GraphDrawer graphDrawer;
 	private List<Pair<Optional<Expression>,Optional<String>>> expressions;
	private List<Boolean> notWellFormedFormula;
	private String error;
	private Parser<Function> parser;
	private GraphFactory graphFactory;
	public ModelImpl() {
    Lexer lexer = LexerImpl.builder()
        .alphabet(new AlphabetToken())
        .extractor(new TokenExtractorImpl())
        .errorFinder(new ErrorFinderSyntax())
        .build();
    Expression expr = ExpressionImpl.of("cos(x)");
    this.parser = ParserImpl.builder()
        .lexer(lexer)
        .alphabet(new AlphabetToken())
        .errorFinderSemantic(new ErrorFinderSemantic())
        .treeFactory(new TokenTreeFactory(new TokenFinder(new AlphabetToken())))
        .build();
		
		graphs = new ArrayList<>();
		camera = Objects.requireNonNull(CameraImpl.getInstance());
		graphDrawer = new GraphDrawerImpl(camera,graphs,camera.getWidthPixels(),camera.getHeightPixels());
		graphFactory = new GraphFactoryImpl(parser);
		expressions = new ArrayList<>();
		notWellFormedFormula = new ArrayList<>();
		// il modello inizia con 2 expressioni vuote
		addExpressions();

	}
	public Camera getCamera() {
		this.camera = CameraImpl.getInstance();
		return camera;
	}
	public boolean createGraph(int i, Expression expr) {
		boolean notWellFormed = false;
    var graph = graphFactory.createGraph(expr);
		if(graph.isPresent()){

			graphs.set(i,graph.get());
      if(graph.get().getError()!= null){

        notWellFormed = true;
        notWellFormedFormula.set(i,true);
        this.error = graph.get().getError();
      }
    }
		
		return notWellFormed;
	}
	@Override
	public List<Graph> getGraphs() {
		return this.graphs;
	}

	public GraphDrawer getGraphDrawer() {
		graphDrawer.setCamera(camera);
		return this.graphDrawer;
	}
	public void addPair(Expression expr,String interval){
		var first = Optional.ofNullable(expr);
		var second = Optional.ofNullable(interval);
		var pair = new Pair<>(first, second);
		expressions.add(pair);

	}
	public void removeLast(){
		List<Graph> graphs = getGraphs();
		if(expressions.size()>1) {
			expressions.remove(expressions.size() - 1);
		}
		if(graphs.size()>1){
			graphs.remove(graphs.size()-1);
		}
	}
	@Override
	public void setExpression(int i, Expression expr){
		while(expressions.size()<i){
			expressions.add(new Pair<>(Optional.empty(),Optional.empty()));
		}
		expressions.get(i).setFirst(Optional.ofNullable(expr));
	}
	@Override
	public void setInterval(int i, String interval){
		expressions.get(i).setSecond(Optional.ofNullable(interval));
	}

	@Override
	public void updateGraph(int i,String interval) {
		Graph graph = graphs.get(i);
		// usually they are null after creating the textbox
		graph.updateGraph(interval, camera);
	}
	public void updateGraphs(){
		for(Graph graph : graphs){
			if(graph != null)// usually they are null after creating the textbox
			  graph.updateGraph(camera);
		}

	}
	public void addExpressions(){
		expressions.add(new Pair<>(Optional.empty(),Optional.empty()));
		notWellFormedFormula.add(false);
		graphs.add(null);

	}
	public Optional<String> getError(){
		return Optional.ofNullable(this.error);
	}


}
