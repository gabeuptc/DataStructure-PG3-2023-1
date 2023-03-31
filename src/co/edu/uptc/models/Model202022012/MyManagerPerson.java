package co.edu.uptc.models.Model202022012;

import co.edu.uptc.model.BinaryTree202022012;
import co.edu.uptc.pojos.Person;
import java.util.ArrayList;
import java.util.List;

public class MyManagerPerson {

   BinaryTree202022012<Person> listPeople;


    private MyManagerModel managerGeneral;
    public MyManagerPerson(MyManagerModel managerGeneral) {
        PersonComparator personComparator = new PersonComparator();
        this.managerGeneral = managerGeneral;
        listPeople = new BinaryTree202022012<>(personComparator);
    }

    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
           managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            listPeople.add(person);
            //listPeople.initData();
            managerGeneral.presenter.notifyPeopleUpdated();
        }
   }

   public Person getPerson(String attribute){
        managerGeneral.presenter.notifyError("Funcionalidad desactivada");
//        listPeople.search()
//       for (Person per: listPeople) {
//           if(attribute.equals(per.getCode()) || attribute.equals(per.getName())){
//               return per;
//           }
//       }
       return null;
   }

   public void editPerson(Person person){
       managerGeneral.presenter.notifyError("Funcionalidad desactivada");
//       for (Person per: listPeople) {
//           if(per.getId()==person.getId()){
//               per.setCode(person.getCode());
//               per.setName(person.getName());
//               return;
//           }
//       }
   }

    public List<Person> getPeople() {
        List<Person> auxList = new ArrayList<>();
    //    auxList.add(new Person(1,"8383","dd"));

        if(listPeople.getHeader() != null) {
            auxList = (List<Person>) listPeople.getList();
            //System.out.println(treeList);
            //auxList.addAll(treeList);
        }


        System.out.println("1111: " + auxList);
        return auxList;

    }
}
