package co.edu.uptc.models.modelGerman;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContratBills;

import java.util.List;


public class ManagerModel implements ContratBills.Model {
    ContratBills.Presenter presenter;

    private ManagerPerson managerPerson;

    public ManagerModel() {
         managerPerson = new ManagerPerson(this);
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
        return "German Amezquita Becerra";
    }
}
