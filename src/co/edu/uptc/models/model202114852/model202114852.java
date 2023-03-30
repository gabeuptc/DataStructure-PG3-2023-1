package co.edu.uptc.models.model202114852;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContratBills;

import java.util.List;

public class model202114852 implements ContratBills.Model {

    ContratBills.Presenter presenter;
    private ManagerPerson202114852 managerPerson;

    public model202114852(){
        managerPerson = new ManagerPerson202114852(this);
    }

    @Override
    public void setPresenter(ContratBills.Presenter presenter) {

    }

    @Override
    public void addPerson(Person person) {
    managerPerson.add(person.clone());
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

    }


    @Override
    public String getAuthor() {
        return "Oscar Ivan Rojas Cuesta";
    }
}
