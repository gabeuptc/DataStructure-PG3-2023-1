package co.edu.uptc.models;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenters.ContratBills;

import java.util.List;


public class ManagerGeneral implements ContratBills.Model {
    ContratBills.Presenter presenter;

    private ManagerPerson managerPerson;

    public ManagerGeneral() {
         managerPerson = new ManagerPerson(this);
    }

    @Override
    public void setPresenter(ContratBills.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(Person person) {
           managerPerson.addPerson(person);
    }

    @Override
    public String getPerson(int pos) {
        return null;
    }

    @Override
    public List<Person> getPeople() {

        List<Person> auxList = managerPerson.getPeople();
       return auxList;
    }

    @Override
    public Person getPerson(String attribute) {
        return managerPerson.getPerson(attribute);
    }

    @Override
    public void editPerson(Person person) {
        managerPerson.editPerson(person);
    }
}
