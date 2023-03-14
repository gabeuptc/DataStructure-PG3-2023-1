package co.edu.uptc.models;

import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.List;

public class ManagerPerson {
   List<Person> listPeople;

    private ManagerGeneral managerGeneral;
    public ManagerPerson(ManagerGeneral managerGeneral) {
        this.managerGeneral = managerGeneral;
        listPeople = new ArrayList<>();
    }

    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
           managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            listPeople.add(person);
            managerGeneral.presenter.notifyPeopleUpdated();
        }
   }


    public List<Person> getPeople() {
        return new ArrayList<Person>(listPeople);

    }
}
