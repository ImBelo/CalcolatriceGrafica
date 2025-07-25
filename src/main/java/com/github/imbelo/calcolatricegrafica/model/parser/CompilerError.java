package com.github.imbelo.calcolatricegrafica.model.parser;

public abstract class CompilerError { 
  protected final String message;
  protected final int index;
  protected final int position; 
  public CompilerError(String message, int index, int position) {
    this.message = message;
    this.index = index;
    this.position = position;
   }


  public String getMessage() { return message; }
  public int getIndex() { return index; }
  public int getPosition() { return position; }

  @Override
  public String toString() {
    return String.format("Error at position %d, cell %d: %s", 
     index, position, message);
  }
}

