package co.edu.uptc.presenter;

import co.edu.uptc.models.graphs.a202127812Graphs.ManagerModelGraphs202127812;
import co.edu.uptc.models.people.model202128687.ManagerModel202128687;
import co.edu.uptc.models.people.model202114641.ManagerModel202114641;
import co.edu.uptc.models.people.model202127343.ManagerModel202127343;
import co.edu.uptc.models.people.model202022012.ManagerModel202022012;
import co.edu.uptc.models.people.model202127717.ManagerModel202127717;
import co.edu.uptc.models.people.model202128778.Manager202128778;
import co.edu.uptc.models.people.model202127812.ManagerModel202127812;
import co.edu.uptc.models.people.model202127061.ManagerModel202127061;
import co.edu.uptc.models.people.modelGerman.ManagerModel;

import co.edu.uptc.models.people.model202113049.ManagerModel_202113049;


import co.edu.uptc.models.people.model202115100.ManegerModel202115100;

import co.edu.uptc.views.board.DashBoard;
import co.edu.uptc.views.maps.ManagerGraphs;


public class ManagerGeneral {
    private static ManagerGeneral instance;
    ContractPeople.View view;
    ContractGraphs.View viewGraphs;
    ContractPeople.Model modelGerman;
    ContractPeople.Model model202127061;
    ContractPeople.Model model202113059;
    ContractPeople.Model model202128687;
    ContractPeople.Model model202128778;
    ContractPeople.Model  model202127343;
    ContractPeople.Model model202022012;
    ContractPeople.Presenter presenter;
    ContractGraphs.Presenter presenterGraphs;
    ContractPeople.Model model202127812;
    ContractPeople.Model modelSebastian;
    ContractPeople.Model model202115100;
    ContractPeople.Model model202127717;

    ContractGraphs.Model modelGraphs202127812;
    ContractGraphs.Model modelGraphsPrueba;

    private ManagerGeneral() {
    }

    private void createMVP(){
         view = new DashBoard();
         viewGraphs = ManagerGraphs.getInstance();
         presenter = new Presenter();
         presenterGraphs = new PresenterGraphs();
         view.setPresenter(presenter);
         viewGraphs.setPresenter(presenterGraphs);
         presenter.setView(view);
         presenterGraphs.setView(viewGraphs);
         createModels();
         createModelsGraphs();
         configModelUserGerman();
         configModelUserSantiago_202115100();
         configModelGraphs202127812();
     }

     public static ManagerGeneral getInstance(){
         return instance==null?instance = new ManagerGeneral():instance;
     }


     private void createModels(){
         // TODO aqui se cream todos los modelos
         modelGerman = new ManagerModel();
         model202128687 = new ManagerModel202128687();
         model202128778 = new Manager202128778();
         model202113059 = new ManagerModel_202113049();
         model202127343 = new ManagerModel202127343();
         model202022012 = new ManagerModel202022012();
         model202127812 = new ManagerModel202127812();
         model202127061 = new ManagerModel202127061();
         modelSebastian= new ManagerModel202114641();
         model202115100 = new ManegerModel202115100();
         model202127717 = new ManagerModel202127717();
     }

     private void createModelsGraphs(){
         // TODO aqui se cream todos los modelos de grafos
        modelGraphs202127812 = new ManagerModelGraphs202127812();
        modelGraphsPrueba = new ManagerModelGraphs202127812();
     }

     public void configModelUserGerman(){
         modelGerman.setPresenter(presenter);
         presenter.setModel(modelGerman);
         view.updatedPeople();
     }

    public void configModel202113049(){
        model202113059.setPresenter(presenter);
        presenter.setModel(model202113059);
        view.updatedPeople();
    }

    public void configModel202127717(){
        model202127717.setPresenter(presenter);
        presenter.setModel(model202127717);
        view.updatedPeople();
    }


    public void configModelUser202114641(){
        modelSebastian.setPresenter(presenter);
        presenter.setModel(modelSebastian);
        view.updatedPeople();
    }



    public void configModelUser202127343(){
        model202127343.setPresenter(presenter);
        presenter.setModel(model202127343);
        view.updatedPeople();
    }

    public void configModelOtherAlex() {
        presenter.setModel(model202128687);
        view.updatedPeople();
    }
    public void configModelUser202127061(){
        model202127061.setPresenter(presenter);
        presenter.setModel(model202127061);
        view.updatedPeople();
    }
    public void configModelUserSantiago_202115100(){
        model202115100.setPresenter(presenter);
        presenter.setModel(model202115100);
        view.updatedPeople();
    }

    public void configModel202127812(){
        model202127812.setPresenter(presenter);
        presenter.setModel(model202127812);
        view.updatedPeople();
    }


    public void configBryanModel(){
        // TODO  aqui se configura para cado estudiante el modelo y este metodo se llama desde la vista
        model202022012.setPresenter(presenter);
        presenter.setModel(model202022012);
        view.updatedPeople();
    }

    public void configModel202128778(){
        model202128778.setPresenter(presenter);
        presenter.setModel(model202128778);
        view.updatedPeople();
    }

    public void configModelGraphs202127812(){
        // TODO  configurar modelo de grafos - guia
        modelGraphs202127812.setPresenter(presenterGraphs);
        presenterGraphs.setModel(modelGraphs202127812);
        viewGraphs.updateGraph();
    }

    public void configModelGraphsPrueba(){
        // TODO  configurar modelo de grafos - guia
        modelGraphsPrueba.setPresenter(presenterGraphs);
        presenterGraphs.setModel(modelGraphsPrueba);
        viewGraphs.updateGraph();
    }

    public void runProject(){
        createMVP();
        view.start();
    }

}
