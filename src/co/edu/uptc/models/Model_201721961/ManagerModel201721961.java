package co.edu.uptc.models.Model_201721961;

import co.edu.uptc.models.ModelGerman.ManagerPerson;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenters.ContratBills;

import java.util.List;

public class ManagerModel201721961 implements ContratBills.Model {


    ContratBills.Presenter presenter;

    private ManagerPerson managerPerson;


    @Override
    public void setPresenter(ContratBills.Presenter presenter) {

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

    }

    @Override
    public String getAuthor() {
        return null;
    }
}
