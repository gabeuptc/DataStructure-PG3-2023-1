package co.edu.uptc.models.model202113214;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.pojos.Person;

public class ManagerPersonOwn {

    TreeUptc treeUptc;

    private MyClass myClass;


    public ManagerPersonOwn(MyClass myClass) {
        this.myClass = myClass;
         treeUptc = new TreeUptc();

    }
    
    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
           myClass.presenter.notifyError("Información incompleta");
        } else {
            treeUptc.add(person);
            myClass.presenter.notifyPeopleUpdated();
        }
   }

   public Person getPerson(String attribute){
      /*  for (Person per: listPeople) {
           if(attribute.equals(per.getCode()) || attribute.equals(per.getName())){
               return per;
           }
       }*/
       return null;
   }
   
   public List<Person> parseIntoArrayList(Node node){
    List<Person> list = new ArrayList<>();
    if(node !=null){
        list.add(node.getInfo());
        list.addAll(parseIntoArrayList(node.getLess()));
        list.addAll(parseIntoArrayList(node.getmajorAndEqual()));
    }

    return list;
}

   public void editPerson(Person person){
       /*for (Person per: listPeople) {
           if(per.getId()==person.getId()){
               per.setCode(person.getCode());
               per.setName(person.getName());*/
               return;
           //}
       //}
   }

   public List<Person> getPeople() {
        return parseIntoArrayList(treeUptc.getHeader());
    }



}
