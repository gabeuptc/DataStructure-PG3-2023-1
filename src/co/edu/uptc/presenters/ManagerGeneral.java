package co.edu.uptc.presenters;

import co.edu.uptc.views.dashBoard.DashBoard;

public class ManagerGeneral {
    ContratBills.View view;
    ContratBills.Model model;
    ContratBills.Presenter preenter;
     public void createMVP(){
            view = new DashBoard();
            model = new co.edu.uptc.models.ManagerGeneral();
            preenter = new Presenter();
            view.setPresenter(preenter);
            model.setPresenter(preenter);
            preenter.setView(view);
            preenter.setModel(model);
     }

    public void runProject(){
        createMVP();
        view.start();
    }

}
