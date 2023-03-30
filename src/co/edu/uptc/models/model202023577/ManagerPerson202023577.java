package co.edu.uptc.models.model202023577;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.tree.Node;
import co.edu.uptc.tree.TreeUptc;

import java.util.ArrayList;
import java.util.List;

public class ManagerPerson202023577 {


    TreeUptc<Person> treeUptc;
   List<Person> listPeople;

    private ManagerModel202023577 managerGeneral;
    public ManagerPerson202023577(ManagerModel202023577 managerGeneral) {
        this.managerGeneral = managerGeneral;
        listPeople = new ArrayList<>();
        treeUptc = new TreeUptc<>((x, y) -> x.getName().compareTo(y.getName()));
    }

    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
           managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            treeUptc.add(person);
            managerGeneral.presenter.notifyPeopleUpdated();
        }
   }

   public Person getPerson(String namePerson){
       return treeUptc.search(new Node<>(new Person(0, "", namePerson))).getInfo();
   }

    public List<Person> getPeople() {
        ArrayList<Person> auxList = treeUptc.getDatas();
        List<Person> result = new ArrayList<>();
        for (Person person: auxList) {
            result.add(person.clone());
        }
        return result;
    }
}
