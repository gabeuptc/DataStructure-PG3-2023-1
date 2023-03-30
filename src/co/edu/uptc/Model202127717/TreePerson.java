package co.edu.uptc.Model202127717;

import co.edu.uptc.pojos.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreePerson {
    private Node header;
    private Person toGet = null;
    private Person toEdit = null;

    private final Comparator<Person> comparator = Comparator.comparing(Person::getName);
    public void add(Person o){
        Node node = new Node(o);
        if (header == null){
            header = node;
        }else {
            Node temp = search(node);
            if (comparator.compare(o,temp.getInfo()) >= 0){
                temp.setGreaterEqual(node);
            }else {
                temp.setMinor(node);
            }
        }
    }

    /*private Node search(Node node){
        Node aux = header;
        boolean isFounded = false;
        while (!isFounded){
            if (comparator.compare(node.getInfo(),aux.getInfo()) < 0){
                if (aux.getMinor() != null){
                    aux = aux.getMinor();
                }else {
                    isFounded = true;
                }
            }else {
                if (aux.getGreaterEqual() != null){
                    aux = aux.getGreaterEqual();
                }else {
                    isFounded = true;
                }
            }
        }
        return aux;
    }*/

    private Node search(Node node) {
        Node current = header;
        boolean found = false;
        while (!found) {
            int comparisonResult = comparator.compare(node.getInfo(), current.getInfo());

            if (comparisonResult < 0) {
                if (current.getMinor() != null) {
                    current = current.getMinor();
                } else {
                    found = true;
                }
            }
            else {
                if (current.getGreaterEqual() != null) {
                    current = current.getGreaterEqual();
                } else {
                    found = true;
                }
            }
        }

        return current;
    }

    private Node search(Person value) {
        if (header == null) {
            return null;
        }

        Node current = header;
        boolean found = false;

        while (!found) {
            int comparisonResult = comparator.compare(value, current.getInfo());

            if (comparisonResult < 0) {
                if (current.getMinor() != null) {
                    current = current.getMinor();
                } else {
                    found = true;
                }
            }

            else if (comparisonResult == 0) {
                return current;
            }

            else {
                if (current.getGreaterEqual() != null) {
                    current = current.getGreaterEqual();
                } else {
                    found = true;
                }
            }
        }

        return null;
    }

    /*private Node search(Person value){
        if (header == null){
            return null;
        }
        Node aux = header;
        boolean isFounded = false;
        while (!isFounded){
            if (comparator.compare(value,aux.getInfo()) < 0){
                if (aux.getMinor() != null){
                    aux = aux.getMinor();
                }else {
                    isFounded = true;
                }
            }else {
                if (comparator.compare(value,aux.getInfo()) == 0){
                    return aux;
                }
                if (aux.getGreaterEqual() != null){
                    aux = aux.getGreaterEqual();
                }else {
                    isFounded = true;
                }
            }
        }
        return null;
    }*/
    public List<Person> getListFromTree(){
        List<Person> list = new ArrayList<>();
        if (header != null){
            getTreePrev(header,list);
        }
        return list;
    }

    public void getTreePrev(Node node, List<Person> list){
        if (node.getMinor() != null){
            getTreePrev(node.getMinor(),list);
        }
        list.add(node.getInfo().clone());
        if (node.getGreaterEqual() != null){
            getTreePrev(node.getGreaterEqual(),list);
        }
    }
    public boolean remove(Person value){
        Node toRemove = search(value);
        if (toRemove != null){
            Node toAdd;
            Node before = getBeforeTo(toRemove);
            if (before == null){
                toAdd = header.getMinor();
                header = header.getGreaterEqual();
            }else {
                if (comparator.compare(before.getInfo(),toRemove.getInfo()) < 0){
                    toAdd = toRemove.getMinor();
                    before.setGreaterEqual(toRemove.getGreaterEqual());
                }else {
                    toAdd= toRemove.getGreaterEqual();
                    before.setMinor(toRemove.getMinor());
                }
            }
            if (toAdd!=null){
                Node temp = search(toAdd);
                if (comparator.compare(toAdd.getInfo(),temp.getInfo()) >= 0){
                    temp.setGreaterEqual(toAdd);
                }else {
                    temp.setMinor(toAdd);
                }
            }
            return true;
        }
        return false;
    }
    private Node getBeforeTo(Node node){
        if (comparator.compare(node.getInfo(), header.getInfo()) == 0){
            return null;
        }
        Node aux = header;
        boolean isFounded = false;
        while (!isFounded){
            if (comparator.compare(node.getInfo(),aux.getInfo()) < 0){
                if (aux.getMinor() != null){
                    if (node.equals(aux.getMinor())){
                        return aux;
                    }else {
                        aux = aux.getMinor();
                    }
                }else {
                    isFounded = true;
                }
            }else {
                if (aux.getGreaterEqual() != null){
                    if (node.equals(aux.getGreaterEqual())){
                        return aux;
                    }else {
                        aux = aux.getGreaterEqual();
                    }
                }else {
                    isFounded = true;
                }
            }
        }
        return null;
    }
    public Person getPerson(String attribute){
        if (header != null){
            putNodePersonToGet(header,attribute);
        }
        return toGet;
    }
    public void putNodePersonToGet(Node node, String attribute){
        if (node.getMinor() != null){
            putNodePersonToGet(node.getMinor(),attribute);
        }
        if (node.getInfo().getName().equals(attribute)||node.getInfo().getCode().equals(attribute)){
            toGet =node.getInfo();
            return;
        }
        if (node.getGreaterEqual() != null){
            putNodePersonToGet(node.getGreaterEqual(),attribute);
        }
    }
    public void editPerson(Person person){
        if (header != null){
            putPersonToEdit(header,person.getId());
        }
        toEdit.setCode(person.getCode());
        toEdit.setName(person.getName());
    }

    private void putPersonToEdit(Node node, int idPerson) {
        if (node.getMinor() != null){
            putPersonToEdit(node.getMinor(),idPerson);
        }
        if (node.getInfo().getId() == idPerson){
            toEdit = node.getInfo();
            return;
        }
        if (node.getGreaterEqual() != null){
            putPersonToEdit(node.getGreaterEqual(),idPerson);
        }
    }
}
