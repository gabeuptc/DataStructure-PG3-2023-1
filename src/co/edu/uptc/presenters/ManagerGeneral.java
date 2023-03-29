package co.edu.uptc.presenters;

import co.edu.uptc.models.Model202127812.ManagerModel202127812;
import co.edu.uptc.models.ModelGerman.ManagerModel;
import co.edu.uptc.views.dashBoard.DashBoard;

public class ManagerGeneral {
    private static ManagerGeneral instance;
    ContratBills.View view;
    ContratBills.Model modelGerman;
    ContratBills.Presenter presenter;
    ContratBills.Model model202127812;

    private ManagerGeneral() {
    }

    private void createMVP(){
         view = new DashBoard();
         presenter = new Presenter();
         view.setPresenter(presenter);
         presenter.setView(view);
         createModels();
         configModelUserGerman();
     }

     public static ManagerGeneral getInstance(){
         return instance==null?instance = new ManagerGeneral():instance;
     }


     private void createModels(){
         // TODO aqui se cream todos los modelos
         modelGerman = new ManagerModel();
         model202127812 = new ManagerModel202127812();
     }

     public void configModelUserGerman(){
         modelGerman.setPresenter(presenter);
         presenter.setModel(modelGerman);
         view.updatedPeople();
     }


    public void configModelOtherUser(){
        // TODO  aqui se configura para cado estudiante el modelo y este metodo se llama desde la vista
        //modelGerman.setPresenter(presenter);
        presenter.setModel(null);
        view.updatedPeople();
    }
    public void configModel202127812(){
        model202127812.setPresenter(presenter);
        presenter.setModel(model202127812);
        view.updatedPeople();
    }


    public void runProject(){
        createMVP();
        view.start();
    }

}
