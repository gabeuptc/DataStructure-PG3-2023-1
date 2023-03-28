package co.edu.uptc.presenters;

import co.edu.uptc.models.model202127061.ManagerModel202127061;
import co.edu.uptc.models.ModelGerman.ManagerModel;
import co.edu.uptc.views.dashBoard.DashBoard;

public class ManagerGeneral {
    private static ManagerGeneral instance;
    ContratBills.View view;
    ContratBills.Model modelGerman;
    ContratBills.Model model202127061;
    ContratBills.Presenter presenter;

    private ManagerGeneral() {
    }

    private void createMVP(){
         view = new DashBoard();
         presenter = new Presenter();
         view.setPresenter(presenter);
         presenter.setView(view);
         createModels();
         configModelUserGerman();
         configModelUser202127061();
    }

     public static ManagerGeneral getInstance(){
         return instance==null?instance = new ManagerGeneral():instance;
     }


     private void createModels(){
         // TODO aqui se cream todos los modelos
         modelGerman = new ManagerModel();
         model202127061 = new ManagerModel202127061();
     }

     public void configModelUserGerman(){
         modelGerman.setPresenter(presenter);
         presenter.setModel(modelGerman);
         view.updatedPeople();
     }

    public void configModelUser202127061(){
        model202127061.setPresenter(presenter);
        presenter.setModel(model202127061);
        view.updatedPeople();
    }

    public void configModelOtherUser(){
        // TODO  aqui se configura para cado estudiante el modelo y este metodo se llama desde la vista
        //modelGerman.setPresenter(presenter);
        presenter.setModel(null);
        view.updatedPeople();
    }


    public void runProject(){
        createMVP();
        view.start();
    }

}
