package co.edu.uptc.models.model202022012;

import co.edu.uptc.model.BinaryTree202022012;
import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.List;

public class ManagerPerson202022012 {

   BinaryTree202022012<Person> listPeople;
   private ManagerModel202022012 managerGeneral;
    public ManagerPerson202022012(ManagerModel202022012 managerGeneral) {
        PersonComparator personComparator = new PersonComparator();
        this.managerGeneral = managerGeneral;
        listPeople = new BinaryTree202022012<>(personComparator);
    }

    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
           managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            listPeople.add(person);
            managerGeneral.presenter.notifyPeopleUpdated();
        }
   }

   public Person getPerson(String attribute){
       for (Person per: listPeople.getList()) {
           if(attribute.equals(per.getCode()) || attribute.equals(per.getName())){
               return per;
           }
       }
       return null;
   }

   public void editPerson(Person person){
       for (Person per: listPeople.getList()) {
           if(per.getId()==person.getId()){
               per.setCode(person.getCode());
               per.setName(person.getName());
               return;
           }
       }
   }

    public List<Person> getPeople() {
        List<Person> auxList = new ArrayList<>();
        if(listPeople.getHeader() != null) {
            for (Person per: listPeople.getList()) {
                auxList.add(per.clone());
            }
        }
        return auxList;

    }
}
