package com.github.imbelo.calcolatricegrafica.controller.events;

public class ChangeTextBoxEvent implements ViewEvent {
    private String text;
    private int index;

    public ChangeTextBoxEvent(String text,int index){
        this.text = text;
        this.index = index;
    }
    public String getText(){
        return this.text;
    }
    public int getIndex(){
        return index;
    }
}
