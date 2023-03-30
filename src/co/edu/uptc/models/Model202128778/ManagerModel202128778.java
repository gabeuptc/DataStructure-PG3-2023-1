package co.edu.uptc.models.Model202128778;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContratBills;

import java.util.List;


public class ManagerModel202128778 implements ContratBills.Model {
    ContratBills.Presenter presenter;

    private ManagerPerson managerPerson;

    public ManagerModel202128778() {
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

        List<Person> auxList = managerPerson.getListofTree();
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
        return "Pedro Eduardo Cruz López";
    }
}
