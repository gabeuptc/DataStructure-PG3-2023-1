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
        return new ArrayList<Person>(listPeople);

    }
}
