
package co.edu.uptc.presenter;

import co.edu.uptc.pojos.Person;

import java.util.List;


public class Presenter implements ContractPeople.Presenter{
    private ContractPeople.View view;
    private ContractPeople.Model model;

    @Override
    public void setView(ContractPeople.View view) {
        this.view = view;
    }

    @Override
    public void setModel(ContractPeople.Model model) {
    this.model=model;
    }

    @Override
    public void addPersonInModel(Person person) {
        if (validaModel()){
            model.addPerson(person);
        }
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
        return validaModel()? model.getPerson(attribute): null;
    }

    private boolean validaModel(){
        boolean validated = false;
        if (model !=null) {
              validated = true;
        } else {
            view.notifyError("No existe modelo seleccionado");
        }
        return validated;
    }

    @Override
    public void editPerson(Person person) {
        if (validaModel()) {
            model.editPerson(person);
        }
    }

    @Override
    public List<Person> getPeople() {
       return validaModel()? model.getPeople():null;
    }

    @Override
    public void notifyError(String value) {
        view.notifyError(value);
    }

    @Override
    public String getAuthor() {
        return validaModel()?model.getAuthor():"No hay modelo seleccionado";
    }


}

