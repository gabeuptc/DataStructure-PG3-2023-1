package co.edu.uptc.models.Alex202128687;

import co.edu.uptc.pojos.Person;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ManagerPersonAlex{
    // cambiar la lista por el arbol
    private List<Person> listPeople;
    private ManagerModelAlex managerGeneral;
    private TreeSearch<Person> tree;
    private PersonCompare comparator;

    public ManagerPersonAlex(ManagerModelAlex managerGeneral) {
        this.managerGeneral = managerGeneral;
        listPeople = new ArrayList<>();
        comparator = new PersonCompare();
        tree = new TreeSearch(comparator);
    }

    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
            managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            tree.add(person);
            listPeople.removeAll(listPeople);
            listPeople.addAll(tree.getList());
            managerGeneral.presenter.notifyPeopleUpdated();
        }
    }

    public Person getPerson(String attribute){
        for (Person per: listPeople) {
            if(attribute.equals(per.getCode()) || attribute.equals(per.getName())){
                return per;
            }
        }
        return null;
    }

    public void editPerson(Person person){
        for (Person per: listPeople) {
            if(per.getId()==person.getId()){
                per.setCode(person.getCode());
                per.setName(person.getName());
                return;
            }
        }
    }

    public List<Person> getPeople() {
        List<Person> auxList = new ArrayList<>();
        for (Person person: listPeople) {
            auxList.add(person.clone());
        }
        return auxList;

    }
}
