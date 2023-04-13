package co.edu.uptc.models.people.model202127343;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContractPeople;

import java.util.List;


public class ManagerModel202127343 implements ContractPeople.Model {
    ContractPeople.Presenter presenter;

    private ManagerPerson managerPerson;

    public ManagerModel202127343() {
         managerPerson = new ManagerPerson(this);
    }

    @Override
    public void setPresenter(ContractPeople.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(Person person) {
        managerPerson.addPerson(new Person(person.getId(), person.getCode(), person.getName()));
    }

    @Override
    public String getPerson(int pos) {
        return null;
    }

    @Override
    public List<Person> getPeople() {List<Person> auxList = managerPerson.getPeople();
       return auxList;
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
        return "Cristian David Tamayo Cortes";
    }
}
