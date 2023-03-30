package co.edu.uptc.models.Model202128778;

import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreeUptc {

    private Node<Person> header;
    private ComparatorPerson comparatorPerson;

    public TreeUptc(){
        comparatorPerson = new ComparatorPerson();
    }

    public void add(Person person) {
        Node<Person> aux = new Node(person);
        if (header == null) {
            header = aux;
        } else {
            Node<Person> tmp = search(aux);
            int tmpComparate = comparatorPerson.compare(aux.getInfo(), tmp.getInfo());
            if (tmpComparate == 0 || tmpComparate == 1)
                tmp.setMejorAndEqual(aux);
            else {
                tmp.setLess(aux);
            }
        }
    }

    public ArrayList<Person> getArray() {
        if(header == null){
            return null;
        } else {
            return toThree(header);
        }
    }
    public ArrayList<Person> toThree(Node<Person> node) {
        ArrayList<Person> auxList = new ArrayList<>();
        if(node != null){
            auxList.addAll(toThree(node.getLess()));
            auxList.add(node.getInfo());
            auxList.addAll(toThree(node.getMejorAndEqual()));
        }
        return auxList;
    }



    public Node search(Node<Person> node){
        Node<Person> aux = header;
        boolean found = false;
        while (!found){
            if (comparatorPerson.compare(node.getInfo(),aux.getInfo()) >= 0){
                if (aux.getLess() != null){
                    aux = aux.getLess();
                }else{
                    found = true;
                }
            }else{
                if (aux.getMejorAndEqual()!=null){
                    aux=aux.getMejorAndEqual();
                }else{
                    found = true;
                }
            }
        }
        return aux;
    }
    public void remove(int info){
        Node nodeRemove = new Node(info);
        search(nodeRemove).setInfo(info);
    }

}
