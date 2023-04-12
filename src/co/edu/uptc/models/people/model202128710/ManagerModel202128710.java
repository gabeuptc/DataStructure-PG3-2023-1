package co.edu.uptc.models.people.model202128710;


import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContractPeople;

import java.util.List;


public class ManagerModel202128710 implements ContractPeople.Model {


    ContractPeople.Presenter presenter;
    private ManagerPerson202128710 managerPerson202128710;

    public ManagerModel202128710(){
        managerPerson202128710 = new ManagerPerson202128710(this);
    }

    @Override
    public void setPresenter(ContractPeople.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(Person person) {
        managerPerson202128710.addPerson(person);
    }

    @Override
    public String getPerson(int pos) {
        return null;
    }

    @Override
    public List<Person> getPeople() {
        return managerPerson202128710.getPerson();
    }

    @Override
    public Person getPerson(String attribute) {
        return managerPerson202128710.getPerson(attribute);
    }

    @Override
    public void editPerson(Person person) {
        managerPerson202128710.editPerson(person);
    }

    @Override
    public String getAuthor() {
        return "Juan Sebastian Rodriguez Mateus";
    }
}
