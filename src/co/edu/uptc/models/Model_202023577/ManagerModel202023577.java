package co.edu.uptc.models.Model_202023577;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenters.ContratBills;

import java.util.ArrayList;
import java.util.List;

public class ManagerModel202023577 implements ContratBills.Model {
    ContratBills.Presenter presenter;

    private ManagerPerson202023577 managerPerson;

    public ManagerModel202023577() {
         managerPerson = new ManagerPerson202023577(this);
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
        List<Person> auxList = managerPerson.getPeople();
       return auxList;
    }

    @Override
    public Person getPerson(String namePerson) {
        return managerPerson.getPerson(namePerson);
    }

    @Override
    public void editPerson(Person person) {

    }

    @Override
    public String getAuthor() {
        return "Nicolas Esteban Avella";
    }
}
