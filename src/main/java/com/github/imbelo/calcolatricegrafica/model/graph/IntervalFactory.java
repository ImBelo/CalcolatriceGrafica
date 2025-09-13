package com.github.imbelo.calcolatricegrafica.model.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Interval;

public class IntervalFactory {
	public static Interval[] createInterval(String text) {
		if(text == null)
			return null;
		List<Interval> intervals = new LinkedList<>();
		String regex = "\\(([-?0-9?.]+);([-?0-9?.]+)\\)*";
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(text);
	    while(m.find()) {
	    	String num1 = m.group(1);
	    	String num2 = m.group(2);
	    	double leftBound = Double.parseDouble(num1);
	    	double rightBound = Double.parseDouble(num2);
	    	intervals.add(new IntervalImpl(leftBound,rightBound));
	    }
	    Interval[] interval = new Interval[intervals.size()];
	    return intervals.toArray(interval);
	}
}
