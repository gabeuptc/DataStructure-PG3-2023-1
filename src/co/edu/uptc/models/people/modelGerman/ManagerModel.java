package co.edu.uptc.models.people.modelGerman;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContractPeople;

import java.util.List;

public class ManagerModel implements ContractPeople.Model {
    public ContractPeople.Presenter presenter;

    private ManagerPerson managerPerson;

    public ManagerModel(){
        managerPerson = new ManagerPerson(this);
    }


    public void setPresenter(ContractPeople.Presenter presenter) {
        this.presenter = presenter;
    }


    public void addPerson(Person person) {
        managerPerson.addPerson(person.clone());
    }

    public String getPerson(int pos) {
        return null;
    }


    public List<Person> getPeople() {
        List<Person> auxList = managerPerson.getPeople();
        return auxList;
    }


    public Person getPerson(String attribute) {
        return managerPerson.getPerson(attribute);
    }


    public void editPerson(Person person) {
        managerPerson.editPerson(person);
    }


    public String getAuthor() {
        return "German Amezquita Becerra";
    }
}
