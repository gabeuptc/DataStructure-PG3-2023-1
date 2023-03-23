package co.edu.uptc.presenters;

import co.edu.uptc.pojos.Person;

import java.util.List;


public class Presenter implements ContratBills.Presenter{
    private ContratBills.View view;
    private ContratBills.Model model;




    @Override
    public void setView(ContratBills.View view) {
        this.view = view;
    }

    @Override
    public void setModel(ContratBills.Model model) {
    this.model=model;
    }

    @Override
    public void addPersonInModel(Person person) {
        model.addPerson(person);
    }

    @Override
    public void notifyPeopleUpdated() {
       view.updatedPeople();
    }



    @Override
    public String getPerson(int pos) {
        return null;
    }

    @Override
    public Person getPerson(String attribute) {
        return model.getPerson(attribute);
    }

    @Override
    public void editPerson(Person person) {
        model.editPerson(person);
    }

    @Override
    public List<Person> getPeople() {
       return  model.getPeople();
    }

    @Override
    public void notifyError(String value) {
        view.notifyError(value);
    }


}
