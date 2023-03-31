package co.edu.uptc.models.model202128710;


import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContratBills;

import java.util.List;


public class ManagerModel202128710 implements ContratBills.Model {


    ContratBills.Presenter presenter;
    private ManagerPerson202128710 managerPerson202128710;

    public ManagerModel202128710(){
        managerPerson202128710 = new ManagerPerson202128710(this);
    }

    @Override
    public void setPresenter(ContratBills.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(Person person) {
        managerPerson202128710.addPerson(person);
    }

    @Override
    public String getPerson(int pos) {
        return null;
    }

    @Override
    public List<Person> getPeople() {
        return managerPerson202128710.getPerson();
    }

    @Override
    public Person getPerson(String attribute) {
        return null;
    }

    @Override
    public void editPerson(Person person) {

    }

    @Override
    public String getAuthor() {
        return "Juan Sebastian Rodriguez Mateus";
    }
}
