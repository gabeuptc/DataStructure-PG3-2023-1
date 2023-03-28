package co.edu.uptc.models.Model202127812;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenters.ContratBills;

import java.util.List;

public class ManagerModel202127812 implements ContratBills.Model {
    ContratBills.Presenter presenter;
    private ManagerPerson managerPerson;

    public ManagerModel202127812() {
        this.managerPerson=new ManagerPerson(this);
    }

    @Override
    public void setPresenter(ContratBills.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void addPerson(Person person) {
        managerPerson.addPerson(person);
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
        return managerPerson.getPerson(attribute).clone();
    }

    @Override
    public void editPerson(Person person) {
        managerPerson.editPerson(person);
    }

    @Override
    public String getAuthor() {
        return "Harold Ricardo Alvarado Leandro";
    }
}
