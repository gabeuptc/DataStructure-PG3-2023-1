package co.edu.uptc.models.Model_202128710;


import co.edu.uptc.model.TreeUptc;
import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.List;

public class Manager_Person_202128710 {

    private List<Person> personList;
    private TreeUptc treePerson;
    private Manager_Model_202128710 managerGeneral;

    public Manager_Person_202128710(Manager_Model_202128710 manager_model_202128710){
        this.managerGeneral = manager_model_202128710;
        treePerson = new TreeUptc<>();
        personList = new ArrayList<>();
    }

    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
            managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            treePerson.add(person,new ComparatorPerson());
            personList.add(person);
            managerGeneral.presenter.notifyPeopleUpdated();
        }
    }

    public List<Person> getPerson(){
        List<Person> cloneListPerson = new ArrayList<>();
        for (Person aux:personList) {
            cloneListPerson.add(aux.clone());
        }
        return cloneListPerson;
    }
}
