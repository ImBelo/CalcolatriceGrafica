package com.github.imbelo.calcolatricegrafica.controller.listeners;

import com.github.imbelo.calcolatricegrafica.controller.events.ViewEvent;


public class GraphDrawerListener implements ViewListener{
    private Runnable action;
    public GraphDrawerListener(Runnable action){
        this.action = action;

    }
    @Override
    public void onViewChanged(ViewEvent event) {
        action.run();
    }
}
