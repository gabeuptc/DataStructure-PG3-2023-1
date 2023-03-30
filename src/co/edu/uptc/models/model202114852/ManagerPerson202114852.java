package co.edu.uptc.models.model202114852;

import co.edu.uptc.pojos.Person;

import java.util.List;

public class ManagerPerson202114852 {

    private Model202114852 model202114852;

    BinaryTree<Person> treePerson;

    public ManagerPerson202114852(Model202114852 model202114852){
        this.model202114852 = new Model202114852();
        treePerson = new BinaryTree<>(new NodeComparator());
    }

    public void add(Person person){
        if(person.getCode().equals("") || person.getName().equals("")){
            model202114852.presenter.notifyError("Informacion incompleta");
        }else{
            treePerson.insert(person);
            model202114852.presenter.notifyPeopleUpdated();
        }
    }

    public List<Person> getPeople(){
        return treePerson.getDatasOrder();
    }

    public Person getPerson(String attribute){
        return treePerson.getPerson(attribute);
    }



}
