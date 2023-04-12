
package co.edu.uptc.models.people.model202115100;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContractPeople;

import java.util.List;

public class ManegerModel202115100 implements ContractPeople.Model {
    ContractPeople.Presenter presenter;
    private ManagerPerson202115100 managerPerson;

    public ManegerModel202115100() {
        managerPerson = new ManagerPerson202115100(this);
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
        return "Santiago Andres Orjuela Lopez";
    }
}
