package co.edu.uptc.models.BryanModel;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenters.ContratBills;

import java.util.List;


public class MyManagerModel implements ContratBills.Model {
    ContratBills.Presenter presenter;

    private MyManagerPerson managerPerson;

    public MyManagerModel() {
         managerPerson = new MyManagerPerson(this);
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
