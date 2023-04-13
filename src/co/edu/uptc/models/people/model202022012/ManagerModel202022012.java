package co.edu.uptc.models.people.model202022012;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContractPeople;

import java.util.List;


public class ManagerModel202022012 implements ContractPeople.Model {
    ContractPeople.Presenter presenter;

    private ManagerPerson202022012 managerPerson;

    public ManagerModel202022012() {
         managerPerson = new ManagerPerson202022012(this);
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

    @Override
    public String getAuthor() {
        return "Bryan Lopez";
    }
}
