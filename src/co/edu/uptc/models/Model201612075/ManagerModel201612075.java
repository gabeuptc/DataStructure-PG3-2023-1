package co.edu.uptc.models.Model201612075;

import java.util.List;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContratBills;

public class ManagerModel201612075 implements ContratBills.Model {
    ContratBills.Presenter presenter;
    private ManagerPerson managerPerson;

    public ManagerModel201612075() {
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
    public List<Person> getPeople() {
        List<Person> auxList = managerPerson.getPeople();
        return auxList;
    }

    @Override
    public String getPerson(int pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPerson'");
    }

    @Override
    public Person getPerson(String attribute) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPerson'");
    }

    @Override
    public void editPerson(Person person) {
        managerPerson.editPerson(person);
    }

    @Override
    public String getAuthor() {
        return "Brayan Julián Barrantes Medina";
    }

}
