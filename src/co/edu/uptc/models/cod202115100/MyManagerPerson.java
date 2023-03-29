package co.edu.uptc.models.cod202115100;

import co.edu.uptc.models.cod202115100.myPojos.BinaryTree;
import co.edu.uptc.pojos.Person;

import java.util.List;

public class MyManagerPerson {
    private MyManager managerGeneral;
    BinaryTree<Person> listPeople;

    public MyManagerPerson(MyManager managerGeneral) {
        this.managerGeneral = managerGeneral;
        listPeople = new BinaryTree<>(new PersonComparator());
    }
    public void addPerson(Person person) {
        if (person.getName().equals("") || person.getCode().equals("")) {
            managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            listPeople.add(person);
            managerGeneral.presenter.notifyPeopleUpdated();
        }
    }

    public List<Person> getPeople() {
        return listPeople.toList(listPeople.getRoot());
    }

    public Person getPerson(String attribute) {
        return listPeople.search(listPeople.getRoot(), attribute);
    }

    public void editPerson(Person person) {
//        listPeople.edit(person);
        System.out.println("editPerson, no implementado");
    }
}
