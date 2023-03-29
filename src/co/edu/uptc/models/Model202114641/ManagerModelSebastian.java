package co.edu.uptc.models.Model202114641;

import co.edu.uptc.models.model202127061.ManagerPerson;
import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenters.ContratBills;

import java.util.List;

public class ManagerModelSebastian implements ContratBills.Model {
    ContratBills.Presenter presenter;

    private ManagerPersonSebastian managerPersonSebastian;

    public ManagerModelSebastian() {
        managerPersonSebastian = new ManagerPersonSebastian(this);
    }

    @Override
    public void setPresenter(ContratBills.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(Person person) {
        managerPersonSebastian.addPerson(person.clone());
    }

    @Override
    public String getPerson(int pos) {
        return null;
    }

    @Override
    public List<Person> getPeople() {

        List<Person> auxList = managerPersonSebastian.getPeople();
        return auxList;
    }

    @Override
    public Person getPerson(String attribute) {
        return managerPersonSebastian.getPerson(attribute);
    }

    @Override
    public void editPerson(Person person) {
        managerPersonSebastian.editPerson(person);
    }

    @Override
    public String getAuthor() {
        return "Jorge Sebastian Mejia";
    }
}
