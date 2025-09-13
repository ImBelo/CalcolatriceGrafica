package com.github.imbelo.calcolatricegrafica.model.parser;

public class SemanticError extends CompilerError {
    private final String symbolName;  

    public SemanticError(String message, int index, int position, 
                        String symbolName) {
        super(message,index ,position);
        this.symbolName = symbolName;
  }

    public String getSymbolName() { return symbolName; }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Symbol '%s')", 
            symbolName );
    }
}
