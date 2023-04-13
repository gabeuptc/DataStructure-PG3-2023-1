package co.edu.uptc.models.people.model202127717;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContractPeople;

import java.util.List;

public class ManagerModel202127717 implements ContractPeople.Model {
    ContractPeople.Presenter presenter;
    private final ManagerPerson managerPerson;

    public ManagerModel202127717() {
        this.managerPerson = new ManagerPerson(this);
    }

    @Override
    public void setPresenter(ContractPeople.Presenter presenter) {
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
        return "Cristian Jesus Celis Gutierrez";
    }
}
