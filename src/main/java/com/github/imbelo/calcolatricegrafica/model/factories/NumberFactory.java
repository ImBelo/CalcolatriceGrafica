package com.github.imbelo.calcolatricegrafica.model.factories;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Expression;
import com.github.imbelo.calcolatricegrafica.model.token.MyNumber;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Token;

public class NumberFactory {

	public static Optional<Token> createNumber(Expression exprLeft) {
		String content = exprLeft.get();
		Pattern pattern = Pattern.compile("^[0-9?.]+");
	    Matcher matcher = pattern.matcher(content);
	    String name="";
	    int index;
	    
	    if (matcher.find()) {
	    	name = matcher.group();
	    	index = matcher.end();
	    	content = content.substring(index);
	    	exprLeft.set(content);
	    	if(content.equals("."))
          return Optional.empty();
	    	return Optional.ofNullable(new MyNumber(Double.parseDouble(name)));	
	    }
	    
		return Optional.empty();	
	}

}
