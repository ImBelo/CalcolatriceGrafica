package com.github.imbelo.calcolatricegrafica.model.parser;

import java.util.ArrayList;
import java.util.List;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Function;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;
import com.github.imbelo.calcolatricegrafica.model.token.Variable;
// class that describes the abstract syntax tree of a mathematical function
public class FunctionImpl implements Function {
	private Token root;
	private List<Variable> variables = new ArrayList<>();
	public FunctionImpl(Token root, List<Variable> variables) {
		this.root = root;
    this.variables = variables;
	}
	// evaluates the function(abstract syntax tree) 
	// in the point that has up to variables.size() dimensions
	public double evaluateAt(double ...nums) {
		// setting variables value
		for(int i = 0; i < Math.min(variables.size(),nums.length);i++) {
			variables.get(i).setNum(nums[i]);
		}
		// calling getValue() on every node of Abstract Syntax Tree
		return this.root!=null?this.root.getValue():0;
	}
	public Token getRoot() {
		return this.root;
	}
	public void setRoot(Token root) {
		this.root=root;
	}
}
