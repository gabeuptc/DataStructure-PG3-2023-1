package co.edu.uptc.presenter;

import co.edu.uptc.models.graphs.modelGraphs202113214.ManagerModelGraphs202113214;
import co.edu.uptc.models.graphs.modelGraphs202022012.ManagerModelGraphs202022012;
import co.edu.uptc.models.graphs.modelGraphs202114641.ManagerModelGraphs202114641;
import co.edu.uptc.models.graphs.modelGraphs202113049.ManagerModelGraphs202113049;
import co.edu.uptc.models.graphs.modelGraphs202115100.ManagerModelGraphs202115100;
import co.edu.uptc.models.graphs.modelGraphs202127061.ManagerModelGraphs202127061;
import co.edu.uptc.models.graphs.modelGraphs202127343.ManagerModelGraphs202127343;
import co.edu.uptc.models.graphs.modelGraphs202127717.ManagerModelGraphs202127717;
import co.edu.uptc.models.graphs.modelGraphs202127812.ManagerModelGraphs202127812;
import co.edu.uptc.models.graphs.modelGraphs202127812.ModelProof;
import co.edu.uptc.models.graphs.modelGraphs202128710.ManagerModelGraphs202128710;
import co.edu.uptc.models.graphs.modelGraphs202128687.ManagerModelGraphs202128687;
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
    ContractPeople.Presenter presenter;
    ContractPeople.Model modelGerman;
    ContractPeople.Model model202127061;
    ContractPeople.Model model202113059;
    ContractPeople.Model model202128687;
    ContractPeople.Model model202128778;
    ContractPeople.Model model202127343;
    ContractPeople.Model model202022012;
    ContractPeople.Model model202127812;
    ContractPeople.Model modelSebastian;
    ContractPeople.Model model202115100;
    ContractPeople.Model model202127717;


    ContractGraphs.Presenter presenterGraphs;
    ContractGraphs.Model modelGraphProof;
    ContractGraphs.Model modelGraphs202022012;
    ContractGraphs.Model modelGraphs202113049;
    ContractGraphs.Model modelGraphs202113214;
    ContractGraphs.Model modelGraphs202114641;
    ContractGraphs.Model modelGraphs202115100;
    ContractGraphs.Model modelGraphs202127061;
    ContractGraphs.Model modelGraphs202127343;
    ContractGraphs.Model modelGraphs202127717;
    ContractGraphs.Model modelGraphs202127812;
    ContractGraphs.Model modelGraphs202128687;
    ContractGraphs.Model modelGraphs202128710;


    private ManagerGeneral() {
    }

    private void createMVP() {
        view = new DashBoard();
        viewGraphs = ManagerGraphs.getInstance();

        presenter = new Presenter();
        presenter.setView(view);

        presenterGraphs = new PresenterGraphs();
        presenterGraphs.setView(viewGraphs);

        view.setPresenter(presenter);
        viewGraphs.setPresenter(presenterGraphs);

        createModelsPeople();
        createModelGraphs();
        configModelUserGerman();
    }

    public static ManagerGeneral getInstance() {
        return instance == null ? instance = new ManagerGeneral() : instance;
    }


    private void createModelsPeople() {

        modelGerman = new ManagerModel();
        model202128687 = new ManagerModel202128687();
        model202128778 = new Manager202128778();
        model202113059 = new ManagerModel_202113049();
        model202127343 = new ManagerModel202127343();
        model202022012 = new ManagerModel202022012();
        model202127812 = new ManagerModel202127812();
        model202127061 = new ManagerModel202127061();
        modelSebastian = new ManagerModel202114641();
        model202115100 = new ManegerModel202115100();
        model202127717 = new ManagerModel202127717();
        model202127343 = new ManagerModel202127343();
    }

    private void createModelGraphs() {
        modelGraphProof = new ModelProof();
        modelGraphs202022012 = new ManagerModelGraphs202022012();
        modelGraphs202113049 = new ManagerModelGraphs202113049();
        modelGraphs202113214 = new ManagerModelGraphs202113214();
        modelGraphs202114641 = new ManagerModelGraphs202114641();
        modelGraphs202115100 = new ManagerModelGraphs202115100();
        modelGraphs202127061 = new ManagerModelGraphs202127061();
        modelGraphs202127343 = new ManagerModelGraphs202127343();
        modelGraphs202127717 = new ManagerModelGraphs202127717();
        modelGraphs202127812 = new ManagerModelGraphs202127812();
        modelGraphs202128687 = new ManagerModelGraphs202128687();
        modelGraphs202128710 = new ManagerModelGraphs202128710();

    }

    public void configModelUserGerman() {
        modelGerman.setPresenter(presenter);
        presenter.setModel(modelGerman);
        view.updatedPeople();
    }

    public void configModel202113049() {
        model202113059.setPresenter(presenter);
        presenter.setModel(model202113059);
        view.updatedPeople();
    }

    public void configModel202127717() {
        model202127717.setPresenter(presenter);
        presenter.setModel(model202127717);
        view.updatedPeople();
    }


    public void configModelUser202114641() {
        modelSebastian.setPresenter(presenter);
        presenter.setModel(modelSebastian);
        view.updatedPeople();
    }


    public void configModelUser202127343() {
        model202127343.setPresenter(presenter);
        presenter.setModel(model202127343);
        view.updatedPeople();
    }

    public void configModelOtherAlex() {
        presenter.setModel(model202128687);
        view.updatedPeople();
    }

    public void configModelUser202127061() {
        model202127061.setPresenter(presenter);
        presenter.setModel(model202127061);
        view.updatedPeople();
    }

    public void configModelUserSantiago_202115100() {
        model202115100.setPresenter(presenter);
        presenter.setModel(model202115100);
        view.updatedPeople();
    }

    public void configModel202127812() {
        model202127812.setPresenter(presenter);
        presenter.setModel(model202127812);
        view.updatedPeople();
    }


    public void configBryanModel() {
        model202022012.setPresenter(presenter);
        presenter.setModel(model202022012);
        view.updatedPeople();
    }

    public void configModel202128778() {
        model202128778.setPresenter(presenter);
        presenter.setModel(model202128778);
        view.updatedPeople();
    }

    public void configModelGraphsProof() {
        try {
            modelGraphProof.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphProof);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphProof.loadGraphs();
            viewGraphs.updateGraph();


        } catch (Exception e) {
            e.printStackTrace();
            viewGraphs.notifyError("Modelo sin definir");
        }
    }

    public void configModelGraphs202022012() {
        try {
            modelGraphs202022012.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202022012);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202022012.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }

    public void configModelGraphs202113049() {
        try {
            modelGraphs202113049.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202113049);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202113049.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }

    public void configModelGraphs202113214() {
        try {
            modelGraphs202113214.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202113214);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202113214.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
            e.printStackTrace();
        }
    }

    public void configModelGraphs202114641() {
        try {
            modelGraphs202114641.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202114641);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202114641.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }

    public void configModelGraphs202115100() {
        try {
            modelGraphs202115100.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202115100);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202115100.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }

    public void configModelGraphs202127061() {
        try {
            modelGraphs202127061.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202127061);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202127061.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }

    public void configModelGraphs202127343() {
        try {
            modelGraphs202127343.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202127343);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202127343.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }

    public void configModelGraphs202127717() {
        try {
            modelGraphs202127717.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202127717);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202127717.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }


    public void configModelGraphs202127812() {
        try {
            modelGraphs202127812.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202127812);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202127812.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }

    public void configModelGraphs202128687() {
        try {
            modelGraphs202128687.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202128687);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202128687.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }

    public void configModelGraphs202128710() {
        try {
            modelGraphs202128710.setPresenter(presenterGraphs);
            presenterGraphs.setModel(modelGraphs202128710);
            viewGraphs.setUser("Modelo de: " + presenterGraphs.getModel().getUser());
            modelGraphs202128710.loadGraphs();
            viewGraphs.updateGraph();
        } catch (Exception e) {
            viewGraphs.notifyError("Modelo sin definir");
        }
    }


    public void runProject() {
        createMVP();
        view.start();
    }

}
