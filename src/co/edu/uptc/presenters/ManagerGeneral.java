package co.edu.uptc.presenters;

import co.edu.uptc.models.Alex202128687.ManagerModelAlex;
import co.edu.uptc.models.Model202127343.ManagerModel202127343;
import co.edu.uptc.models.BryanModel.MyManagerModel;
import co.edu.uptc.models.Model202127812.ManagerModel202127812;
import co.edu.uptc.models.Model_201721961.ManagerModel201721961;
import co.edu.uptc.models.model202127061.ManagerModel202127061;
import co.edu.uptc.models.ModelGerman.ManagerModel;
import co.edu.uptc.models.Pedro.ManagerModel202128778;

import co.edu.uptc.models.Model_202113049.ManagerModel_202113049;
import co.edu.uptc.models.Model_202128710.Manager_Model_202128710;
import co.edu.uptc.models.model_202115100.ModelSantiago_202115100;
import co.edu.uptc.views.dashBoard.DashBoard;

public class ManagerGeneral {
    private static ManagerGeneral instance;
    ContratBills.View view;
    ContratBills.Model modelGerman;
    ContratBills.Model model202127061;
    ContratBills.Model model_202113059;
    ContratBills.Model modelJuan;
    ContratBills.Model modelAlex;

    ContratBills.Model  model202127343;
    ContratBills.Model bryanModel;
    ContratBills.Model modelPedro;
    ContratBills.Model modelSantiago_202115100;
    ContratBills.Presenter presenter;
    ContratBills.Model model202127812;

    ContratBills.Model model_201721961;

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
         modelAlex = new ManagerModelAlex();

         model_201721961 = new ManagerModel201721961();

         model_202113059 = new ManagerModel_202113049();
         modelJuan = new Manager_Model_202128710();

         model202127061 = new ManagerModel();
         model202127343 = new ManagerModel202127343();

         bryanModel = new MyManagerModel();

         modelPedro = new ManagerModel202128778();
         model202127812 = new ManagerModel202127812();
         model202127061 = new ManagerModel202127061();
         modelSantiago_202115100 = new ModelSantiago_202115100();
     }

     public void configModelUserGerman(){
         modelGerman.setPresenter(presenter);
         presenter.setModel(modelGerman);
         view.updatedPeople();
     }

    public void configModelUserBrandon201721961(){
        model_201721961.setPresenter(presenter);
        presenter.setModel(model_201721961);
        view.updatedPeople();
    }

    public void configModel202113049(){
        model_202113059.setPresenter(presenter);
        presenter.setModel(model_202113059);
        view.updatedPeople();
    }



    public void configModelUserJuan(){
        modelGerman.setPresenter(presenter);
        presenter.setModel(modelJuan);
        view.updatedPeople();
    }




    public void configModelUser202127343(){
        model202127343.setPresenter(presenter);
        presenter.setModel(model202127343);
        view.updatedPeople();
    }

    public void configModelOtherAlex() {
        presenter.setModel(modelAlex);
        view.updatedPeople();
    }

    public void configModelUserPedro(){
        modelPedro.setPresenter(presenter);
        presenter.setModel(modelPedro);
        view.updatedPeople();
    }
    public void configModelUser202127061(){
        model202127061.setPresenter(presenter);
        presenter.setModel(model202127061);
        view.updatedPeople();
    }
    public void configModelUserSantiago_202115100(){
        modelSantiago_202115100.setPresenter(presenter);
        presenter.setModel(modelSantiago_202115100);
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
    public void configBryanModel(){
        // TODO  aqui se configura para cado estudiante el modelo y este metodo se llama desde la vista
        bryanModel.setPresenter(presenter);
        presenter.setModel(bryanModel);
        view.updatedPeople();
    }


    public void runProject(){
        createMVP();
        view.start();
    }

}
