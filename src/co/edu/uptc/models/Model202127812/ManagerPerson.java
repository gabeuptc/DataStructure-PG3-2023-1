package co.edu.uptc.models.Model202127812;

import co.edu.uptc.pojos.Person;

import java.util.List;

public class ManagerPerson {
    private ManagerModel202127812 managerModel;
    private TreeUPTCPerson tree;

    public ManagerPerson(ManagerModel202127812 managerModel) {
        this.managerModel = managerModel;
        tree = new TreeUPTCPerson();
    }
    public void addPerson(Person person){
        //TODO revizar funcionamineto
        if (person.getName().equals("") || person.getCode().equals("")) {
            managerModel.presenter.notifyError("Información incompleta");
        } else {
            tree.add(person);
            managerModel.presenter.notifyPeopleUpdated();
        }
    }
    public Person getPerson(String attribute){
        //TODO
        return tree.getPerson(attribute);
    }
    public void editPerson(Person person){
        //TODO
    }
    public List<Person> getPeople(){
        //TODO revizar funcionamineto
        return tree.getListFromTree();
    }
}
