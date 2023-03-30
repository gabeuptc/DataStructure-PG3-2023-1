package co.edu.uptc.models.model202127061;

import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.List;

public class ManagerPerson202127061 {
    List<Person> listPeople;
    private BinaryTreeList<Person> binaryTreeList;
    private ComparatePerson comparatorPerson;

    private ManagerModel202127061 managerGeneral;
    public ManagerPerson202127061(ManagerModel202127061 managerGeneral) {
        this.managerGeneral = managerGeneral;
        listPeople = new ArrayList<>();
        comparatorPerson = new ComparatePerson();
        binaryTreeList = new BinaryTreeList(comparatorPerson);
    }

    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
           managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            binaryTreeList.add(person);
            listPeople.removeAll(listPeople);
            listPeople.addAll(binaryTreeList.getList());
            managerGeneral.presenter.notifyPeopleUpdated();
            //System.out.println("aqui");
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
