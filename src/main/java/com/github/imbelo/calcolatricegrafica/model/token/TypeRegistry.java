package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.*;

import java.util.Map;
import java.util.HashMap;

import java.util.function.*;


public class TypeRegistry {
  private static Map<String, TokenMeta> REGISTRY = new HashMap<>();
  record TokenMeta (Supplier<? extends Token> factory ,Class<? extends Token> type) {}
  public static <T extends Token> void register(
    String symbol, 
    Supplier<T> factory, 
    Class<T> tokenClass
    ) {
        REGISTRY.put(symbol, new TokenMeta(factory, tokenClass));
    }
  static {
        register("+", Sum::new,  BinaryFunction.class);
        register("-", Difference::new, BinaryFunction.class);
        register("*", Product::new,BinaryFunction.class);
        register("/", Division::new, BinaryFunction.class);
        register("^", Pow::new,BinaryFunction.class);
        register("sin", Sin::new, UnaryFunction.class);
        register("cos", Cos::new,UnaryFunction.class);
        register("tan", Tan::new, UnaryFunction.class);
        register("log", Log::new, UnaryFunction.class);
        register("x", VariableX::getInstance, Variable.class);
        register("y", VariableY::getInstance, Variable.class);
        register("(", OpenParentheses::new, AbstractParentheses.class);
        register(")", ClosedParentheses::new, AbstractParentheses.class);
        register("", ()->new MyNumber(0.0), MyNumber.class);
  }
  public static Token create(String symbol) {
   TokenMeta meta = REGISTRY.get(symbol);
     return meta != null ? meta.factory().get() : null;
  }
  public static Map<String,TokenMeta> getRegistry(){
    return REGISTRY;
  }

}
