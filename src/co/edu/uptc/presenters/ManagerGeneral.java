package co.edu.uptc.presenters;

import co.edu.uptc.models.ModelGerman.ManagerModel;
import co.edu.uptc.models.model_202115100.ModelSantiago_202115100;
import co.edu.uptc.views.dashBoard.DashBoard;

public class ManagerGeneral {
    private static ManagerGeneral instance;
    ContratBills.View view;
    ContratBills.Model modelGerman;
    ContratBills.Model modelSantiago_202115100;
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
     }

     public static ManagerGeneral getInstance(){
         return instance==null?instance = new ManagerGeneral():instance;
     }


     private void createModels(){
         // TODO aqui se cream todos los modelos
         modelGerman = new ManagerModel();
         modelSantiago_202115100 = new ModelSantiago_202115100();
     }

     public void configModelUserGerman(){
         modelGerman.setPresenter(presenter);
         presenter.setModel(modelGerman);
         view.updatedPeople();
     }

    public void configModelUserSantiago_202115100(){
        modelSantiago_202115100.setPresenter(presenter);
        presenter.setModel(modelSantiago_202115100);
        view.updatedPeople();
    }

    public void configModelOtherUser(){
        // TODO  aqui se configura para cado estudiante el modelo y este metodo se llama desde la vista
//        modelGerman.setPresenter(presenter);
        presenter.setModel(modelGerman);
        view.updatedPeople();
    }


    public void runProject(){
        createMVP();
        view.start();
    }

}
