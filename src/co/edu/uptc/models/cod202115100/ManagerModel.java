package co.edu.uptc.models.cod202115100;


import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenters.ContratBills;

import java.util.List;

public class ManagerModel implements ContratBills.Model {
    private ContratBills.Presenter presenter;
    private ManagerModel managerPerson;

    @Override
    public void setPresenter(ContratBills.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(Person person) {

    }

    @Override
    public String getPerson(int pos) {
        return null;
    }

    @Override
    public List<Person> getPeople() {
        return null;
    }

    @Override
    public Person getPerson(String attribute) {
        return null;
    }

    @Override
    public void editPerson(Person person) {
//        return managerPerson.editPerson(person);
    }

    @Override
    public String getAuthor() {
        return "Santiago Andrés Orjuela López";
    }
}
