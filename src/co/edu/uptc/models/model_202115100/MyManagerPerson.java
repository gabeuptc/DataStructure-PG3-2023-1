package co.edu.uptc.models.model_202115100;

import co.edu.uptc.models.model_202115100.myPojos.BinaryTree;
import co.edu.uptc.pojos.Person;

import java.util.List;

public class MyManagerPerson {
    private ModelSantiago_202115100 managerGeneral;
    BinaryTree<Person> listPeople;

    public MyManagerPerson(ModelSantiago_202115100 managerGeneral) {
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
        managerGeneral.presenter.notifyError("editPerson, no implementado aun");
    }
}
