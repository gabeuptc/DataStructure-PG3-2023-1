package co.edu.uptc.models.Model201612075;

import java.util.List;
import java.util.ArrayList;

import co.edu.uptc.pojos.Person;

public class ManagerPerson {
    private ManagerModel201612075 managerGeneral;
    List<Person> listPeople;

    public ManagerPerson(ManagerModel201612075 managerModel201612075) {
        this.managerGeneral = managerModel201612075;
        listPeople = new ArrayList<>();
    }

    // PENDIENTE APLICAR CAMBIOS
    public void addPerson(Person person) {
        if (person.getName().equals("") || person.getCode().equals("")) {
            managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            listPeople.add(person);
            managerGeneral.presenter.notifyPeopleUpdated();
        }
    }

    // PENDIENTE APLICAR CAMBIOS
    public Person getPerson(String attribute) {
        for (Person per : listPeople) {
            if (attribute.equals(per.getCode()) || attribute.equals(per.getName())) {
                return per;
            }
        }
        return null;
    }

    public void editPerson(Person person) {
        for (Person per : listPeople) {
            if (per.getId() == person.getId()) {
                per.setCode(person.getCode());
                per.setName(person.getName());
                return;
            }
        }
    }

    public List<Person> getPeople() {
        List<Person> auxList = new ArrayList<>();
        for (Person person : listPeople) {
            auxList.add(person.clone());
        }
        return auxList;

    }
}
