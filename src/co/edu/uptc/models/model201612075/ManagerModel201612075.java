package co.edu.uptc.models.model201612075;

import co.edu.uptc.presenter.ContratBills;
import co.edu.uptc.pojos.Person;

import java.util.List;

public class ManagerModel201612075 implements ContratBills.Model {
    ContratBills.Presenter presenter;
    private final ManagerPerson managerPerson;

    public ManagerModel201612075() {
        this.managerPerson = new ManagerPerson(this);
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
        return managerPerson.getPerson(attribute);
    }

    @Override
    public void editPerson(Person person) {
        managerPerson.editPerson(person);
    }
    @Override
    public String getAuthor() {
        return "Brayan Julián Barrantes Medina";
    }
}
    


