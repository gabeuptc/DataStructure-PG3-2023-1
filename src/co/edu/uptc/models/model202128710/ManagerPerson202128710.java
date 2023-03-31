package co.edu.uptc.models.model202128710;

import co.edu.uptc.model.Tree_202128710;
import co.edu.uptc.pojos.Person;


import java.util.ArrayList;
import java.util.List;

public class ManagerPerson202128710 {

    private List<Person>  listPerson;
    private Tree_202128710<Person> tree_202128710;
    private ManagerModel202128710 managerGeneral;

    public ManagerPerson202128710(ManagerModel202128710 model202128710){
        this.managerGeneral = model202128710;
        tree_202128710 = new Tree_202128710<>();
        listPerson = new ArrayList<>();
    }

    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
            managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            tree_202128710.add(person,new ComparatorPerson202128710());
            listPerson.add(person);
            managerGeneral.presenter.notifyPeopleUpdated();
        }
    }

    public List<Person> getPerson(){
        List<Person> cloneListPerson = new ArrayList<>();
        for (Person aux:listPerson) {
            cloneListPerson.add(aux.clone());
        }
        return cloneListPerson;
    }
}
