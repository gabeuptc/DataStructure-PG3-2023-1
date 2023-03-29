package co.edu.uptc.models.Alex202128687;

import co.edu.uptc.models.ModelGerman.ManagerPerson;
import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenters.ContratBills;

import java.util.List;

public class ManagerModelAlex implements ContratBills.Model {
    ContratBills.Presenter presenter;
    private ManagerPersonAlex managerPerson;
    public ManagerModelAlex(){
        managerPerson = new ManagerPersonAlex(this);
    }

    @Override
    public void setPresenter(ContratBills.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(Person person) {
        System.out.println("para agregar");
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
        return "Alex Duvan Hernandez Buitrago";
    }
}