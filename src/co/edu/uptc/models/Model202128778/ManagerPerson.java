package co.edu.uptc.models.Model202128778;

import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.List;

public class ManagerPerson {
    List<Person> listPeople;
    TreeUptc treeUptc;
    private ManagerModel202128778 managerGeneral;
    public ManagerPerson(ManagerModel202128778 managerGeneral) {
        this.managerGeneral = managerGeneral;
        listPeople = new ArrayList<>();
        treeUptc = new TreeUptc();
    }

    public void addPerson(Person person){
        if (person.getName().equals("") || person.getCode().equals("")) {
            managerGeneral.presenter.notifyError("Información incompleta");
        } else {
            treeUptc.add(person);
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

   public ArrayList<Person> getListofTree(){
        return treeUptc.getArray();
   }

    public TreeUptc getTreeUptc() {
        return treeUptc;
    }

}
