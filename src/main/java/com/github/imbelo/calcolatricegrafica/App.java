package com.github.imbelo.calcolatricegrafica;

import com.github.imbelo.calcolatricegrafica.model.*;
import com.github.imbelo.calcolatricegrafica.view.*;
import com.github.imbelo.calcolatricegrafica.controller.*;

public class App{
  public static void main(String[] args){
    ModelImpl model = new ModelImpl();
		View view = new ViewImpl();
		Controller controller = new ControllerImpl(model,view);
		controller.start();

  }
}
