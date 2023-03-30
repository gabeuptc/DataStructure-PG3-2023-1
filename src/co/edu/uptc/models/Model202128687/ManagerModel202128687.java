package co.edu.uptc.models.Model202128687;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenters.ContratBills;

import java.util.List;

public class ManagerModel202128687 implements ContratBills.Model {
    ContratBills.Presenter presenter;
    private ManagerPerson202128687 managerPerson;
    public ManagerModel202128687(){
        managerPerson = new ManagerPerson202128687(this);
    }

    @Override
    public void setPresenter(ContratBills.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(Person person) {
        managerPerson.addPerson(person.clone());
    }

    @Override
    public String getPerson(int pos) {
        return null;
    }

    @Override
    public List<Person> getPeople() {
        return managerPerson.getPeople();
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