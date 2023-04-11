package co.edu.uptc.models.model201612075;

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
            if (comparator.compare(o,temp.getPerson()) >= 0){
                temp.setGreaterEqual(node);
            }else {
                temp.setMinor(node);
            }
        }
    }

    private Node search(Node node) {
        Node current = header;
        boolean found = false;
        while (!found) {
            int comparisonResult = comparator.compare(node.getPerson(), current.getPerson());

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
            int comparisonResult = comparator.compare(value, current.getPerson());

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

    public List<Person> getListFromTree(){
        List<Person> list = new ArrayList<>();
        if (header != null){
            getTreePreview(header,list);
        }
        return list;
    }

    public void getTreePreview(Node node, List<Person> list){
        if (node.getMinor() != null){
            getTreePreview(node.getMinor(),list);
        }
        list.add(node.getPerson().clone());
        if (node.getGreaterEqual() != null){
            getTreePreview(node.getGreaterEqual(),list);
        }
    }
    public boolean remove(Person value) {
        Node toRemove = search(value);
        if (toRemove != null) {
            Node toAdd;
            Node before = getPreviousNode(toRemove);
            if (before == null) {
                toAdd = header.getMinor();
                header = header.getGreaterEqual();
            } else {
                if (comparator.compare(before.getPerson(),toRemove.getPerson()) < 0) {
                    toAdd = toRemove.getMinor();
                    before.setGreaterEqual(toRemove.getGreaterEqual());
                } else {
                    toAdd= toRemove.getGreaterEqual();
                    before.setMinor(toRemove.getMinor());
                }
            }
            if (toAdd != null) {
                Node temp = search(toAdd);
                if (comparator.compare(toAdd.getPerson(),temp.getPerson()) >= 0) {
                    temp.setGreaterEqual(toAdd);
                } else {
                    temp.setMinor(toAdd);
                }
            }
            return true;
        }
        return false;
    }
    private Node getPreviousNode(Node node) {
        if (comparator.compare(node.getPerson(), header.getPerson()) == 0) {
            return null;
        }
        Node aux = header;
        boolean isFound = false;
        while (!isFound) {
            if (comparator.compare(node.getPerson(), aux.getPerson()) < 0) {
                if (aux.getMinor() != null) {
                    if (node.equals(aux.getMinor())) {
                        return aux;
                    } else {
                        aux = aux.getMinor();
                    }
                } else {
                    isFound = true;
                }
            } else {
                if (aux.getGreaterEqual() != null) {
                    if (node.equals(aux.getGreaterEqual())) {
                        return aux;
                    } else {
                        aux = aux.getGreaterEqual();
                    }
                } else {
                    isFound = true;
                }
            }
        }
        return null;
    }
    public Person getPerson(String attribute){
        if (header != null){
            searchPersonInTree(header,attribute);
        }
        return toGet;
    }
    public void searchPersonInTree(Node node, String attribute) {
        if (node.getMinor() != null) {
            searchPersonInTree(node.getMinor(), attribute);
        }
        if (node.getPerson().getName().equals(attribute) || node.getPerson().getCode().equals(attribute)) {
            toGet = node.getPerson();
            return;
        }
        if (node.getGreaterEqual() != null) {
            searchPersonInTree(node.getGreaterEqual(), attribute);
        }
    }
    public void editPerson(Person person){
        if (header != null){
            searchPersonToEdit(header,person.getId());
        }
        toEdit.setCode(person.getCode());
        toEdit.setName(person.getName());
    }

    private void searchPersonToEdit(Node node, int idPerson) {
        if (node.getMinor() != null) {
            searchPersonToEdit(node.getMinor(), idPerson);
        }
        if (node.getPerson().getId() == idPerson) {
            toEdit = node.getPerson();
            return;
        }
        if (node.getGreaterEqual() != null) {
            searchPersonToEdit(node.getGreaterEqual(), idPerson);
        }
    }
}
