package co.edu.uptc.models.model202113214;

import java.util.List;

import co.edu.uptc.pojos.Person;

import co.edu.uptc.presenter.ContratBills;

public class MyClass implements ContratBills.Model{
    ContratBills.Presenter presenter;

    private ManagerPersonOwn managerPersonOwn;

    public MyClass(){
        managerPersonOwn = new ManagerPersonOwn(this);
    }
   
    @Override
    public void setPresenter(ContratBills.Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void addPerson(Person person) {
        managerPersonOwn.addPerson(person.clone());

    }
    @Override
    public String getPerson(int pos) {
        return null;
    }
    @Override
    public List<Person> getPeople() {
        
        List<Person> auxList = managerPersonOwn.getPeople();
       return auxList;
    }
    @Override
    public Person getPerson(String attribute) {
        return managerPersonOwn.getPerson(attribute);
    }
    @Override
    public void editPerson(Person person) {
        managerPersonOwn.editPerson(person);
    }
    @Override
    public String getAuthor() {
        return "Carlos Manrique";
    }

}
